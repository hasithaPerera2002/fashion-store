package service.impl;

import dto.OrderDTO;
import dto.tableDTO.OrderTableDTO;
import entity.Customer;
import entity.OrderDetails;
import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.*;
import service.OrderService;
import util.Converter;
import util.FactoryConfig;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepo repo = RepoFactory.getInstance().getRepo(RepoTypes.ORDER_REPO);
    OrderDetailsRepo orderDetailsRepo = RepoFactory.getInstance().getRepo(RepoTypes.ORDER_DETAILS);
    StockRepo stockRepo = RepoFactory.getInstance().getRepo(RepoTypes.STOCK_REPO);

    //OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getService(ServiceTypes.ORDER_DETAILS_SERVICE);

    @Override
    public String getNewId() {
        Session session = FactoryConfig.getInstance().getSession();
        try {
            String id = repo.getLastId(session);
            String[] split = id.split("O");
            int i = Integer.parseInt(split[1]);
            return String.format("O%03d", ++i);
        } catch (NullPointerException | NoResultException e) {
            e.printStackTrace();
            return "O001";
        }
    }

    @Override
    public boolean add(ObservableList<OrderTableDTO> tblOrder, Customer customer, double total, String orderId) {
        System.out.println(orderId);
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Orders order = new Orders(orderId, Date.valueOf(LocalDate.now()), total, null, customer);

        try {
            if (0 < this.addOrder(order, session)) {
                System.out.println("order added");
            }

            for (OrderTableDTO to : tblOrder
            ) {

                if (0 < orderDetailsRepo.add(new OrderDetails(order, Converter.getInstance().toStock(to.getStock()), to.getQty(), to.getTotal()), session)) {
                    System.out.println("orderDetails added");
                }
//                int qty = to.getStock().getQty() - to.getQty();
//                stockRepo.updateInTransaction(qty,to.getStock().getId(),session);

            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public int addOrder(Orders order, Session session) {
        String add = repo.add(order, session);
        if (add == null) {
            System.out.println("order not added");
            return 0;
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "ORDER ADDED").show();
            return 1;
        }
    }

    @Override
    public ObservableList<OrderDTO> getChartData() {
        Session session = FactoryConfig.getInstance().getSession();
       try {
            List<Orders> all = repo.getAll(session);
            ObservableList<OrderDTO> observableList = FXCollections.observableArrayList();
            for (Orders o : all
            ) {
                observableList.add(new OrderDTO(o.getDate().toLocalDate(), o.getTotal()));
            }
            return observableList;
        }catch (NoResultException |NullPointerException e){
           e.printStackTrace();
           return null;
       }finally {
           session.close();
       }
    }
}
