package repo;


import entity.OrderDetails;
import org.hibernate.Session;

public interface OrderDetailsRepo {
        int add(OrderDetails orderDetails, Session session);
}
