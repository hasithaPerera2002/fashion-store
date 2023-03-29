package repo.impl;

import entity.Orders;
import org.hibernate.Session;
import repo.OrderRepo;

import java.util.List;

public class OrderRepoImpl implements OrderRepo {
    @Override
    public String add(Orders orders, Session session) {
        return (String) session.save(orders);
    }

    @Override
    public String getLastId(Session session) {

            String id=(String) session.createSQLQuery("SELECT orderId from  orders ORDER BY orderId DESC LIMIT 1").getSingleResult();
            System.out.println(id);
            return id;

    }

    @Override
    public List<Orders> getAll(Session session) {
        List list = session.createCriteria(Orders.class).list();
        return list;
    }
}
