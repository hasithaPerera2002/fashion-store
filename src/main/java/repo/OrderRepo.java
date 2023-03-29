package repo;

import entity.Orders;
import org.hibernate.Session;

import java.util.List;

public interface OrderRepo {
    String add(Orders orders, Session session);

    String getLastId(Session session);

    List<Orders> getAll(Session session);
}
