package service.impl;

import entity.OrderDetails;
import org.hibernate.Session;
import repo.OrderDetailsRepo;
import repo.RepoFactory;
import repo.RepoTypes;
import service.OrderDetailsService;
import util.FactoryConfig;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    OrderDetailsRepo orderDetailsRepo=RepoFactory.getInstance().getRepo(RepoTypes.ORDER_DETAILS);
    @Override
    public int add(OrderDetails orderDetails, Session session) {

        return orderDetailsRepo.add(orderDetails,session);
    }
}
