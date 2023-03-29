package service.impl;

import dto.StockDTO;
import entity.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.RepoFactory;
import repo.RepoTypes;
import repo.StockRepo;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StockService;
import util.Converter;
import util.FactoryConfig;

import javax.persistence.NoResultException;
import java.util.List;

public class StockServiceImpl implements StockService {
    StockRepo stockRepo= RepoFactory.getInstance().getRepo(RepoTypes.STOCK_REPO);

    @Override
    public int add(StockDTO obj) {
        Session session= FactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            stockRepo.add(Converter.getInstance().toStock(obj), session);
            transaction.commit();
            return 1;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }

    }

    @Override
    public int delete(StockDTO obj) {
        Session session=FactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
       try {
            stockRepo.delete(Converter.getInstance().toStock(obj), session);
            transaction.commit();
            return 1;
        }catch (Exception e){
           e.printStackTrace();
           transaction.rollback();
           return 0;
       }finally {
           session.close();
       }
    }

    @Override
    public int update(StockDTO obj) {
        Session session=FactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            stockRepo.update(Converter.getInstance().toStock(obj), session);
            transaction.commit();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }

    }

    @Override
    public String getLastId() throws NoResultException {
        Session session=FactoryConfig.getInstance().getSession();
        try{
            return stockRepo.getLastId(session);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public String getNewId() {
       try {
            String lastId = getLastId();
            String[] split = lastId.split("I");
            int i = Integer.parseInt(split[1]);
            return String.format("I%03d", ++i);
        }catch(NoResultException |NullPointerException e){
           e.printStackTrace();
           return "I001";
       }
    }

    @Override
    public ObservableList<StockDTO> getAll() {
        Session session=FactoryConfig.getInstance().getSession();
        try{
            List<Stock> all = stockRepo.getAll(session);
            ObservableList<StockDTO> observableList = FXCollections.observableArrayList();
            for (Stock s : all
            ) {
                observableList.add(Converter.getInstance().toStockDTO(s));
            }
            return observableList;
        }catch (NoResultException | NullPointerException e){
            return null;
        }finally {
            session.close();
        }
    }


}
