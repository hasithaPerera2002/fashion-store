package repo.impl;


import entity.OrderDetails;
import org.hibernate.Session;
import repo.OrderDetailsRepo;

public class OrderDetailsRepoImpl implements OrderDetailsRepo {
    @Override
    public int add(OrderDetails orderDetails, Session session) {
        session.save(orderDetails);
        return 1;
    }
}
