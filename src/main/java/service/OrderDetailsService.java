package service;

import entity.OrderDetails;
import org.hibernate.Session;

public interface OrderDetailsService {
    int add(OrderDetails orderDetails, Session session);
}
