package service.impl;

import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.CustomerRepo;
import repo.RepoFactory;
import repo.RepoTypes;
import service.CustomerService;
import util.Converter;
import util.FactoryConfig;

import javax.persistence.NoResultException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepo customerRepo= RepoFactory.getInstance().getRepo(RepoTypes.CUSTOMER_REPO);

    @Override
    public int add(CustomerDTO obj) {
        Session session= FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (customerRepo.add(Converter.getInstance().toCustomer(obj), session)!=null) {
                transaction.commit();
                return 1;
            }
            return 0;
        }catch (Exception e){
           e.printStackTrace();
           transaction.rollback();
           return 0;
       }finally {
           session.close();
       }

    }

    @Override
    public int delete(CustomerDTO obj) {
        Session session=FactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            customerRepo.delete(Converter.getInstance().toCustomer(obj), session);
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
    public int update(CustomerDTO obj) {
       Session session=FactoryConfig.getInstance().getSession();
       Transaction transaction= session.beginTransaction();
        try{
            customerRepo.update(Converter.getInstance().toCustomer(obj), session);
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
        Session session= FactoryConfig.getInstance().getSession();


        try {
            return customerRepo.getLastId(session);
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
            String[] split = lastId.split("[C]");
            int i = Integer.parseInt(split[1]);
            return String.format("C%03d", ++i);
        }catch(NoResultException |NullPointerException e){
         e.printStackTrace();
         return "C001";
       }
    }

    @Override
    public ObservableList<CustomerDTO> getAll() {
        Session session=FactoryConfig.getInstance().getSession();

        ObservableList<CustomerDTO>observableList = FXCollections.observableArrayList();
       try {
            List<Customer> all = customerRepo.getAll(session);
           for (Customer c:all
                ) {
               System.out.println(c);
               observableList.add( Converter.getInstance().toCustomerDTO(c));
           }
           return observableList;

        }catch(NullPointerException e){
           e.printStackTrace();

           return observableList;
       }finally {
           session.close();
       }

    }

}
