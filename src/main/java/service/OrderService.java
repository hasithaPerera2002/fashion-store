package service;

import dto.OrderDTO;
import dto.StockDTO;
import dto.tableDTO.OrderTableDTO;
import entity.Customer;
import entity.Orders;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public interface OrderService {


    String getNewId();

    boolean add(ObservableList<OrderTableDTO> tblOrder, Customer toCustomer, double setTotal, String text);

    int addOrder(Orders order, Session session);

    ObservableList<OrderDTO> getChartData();
}
