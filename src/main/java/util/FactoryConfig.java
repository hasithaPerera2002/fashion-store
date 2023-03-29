package util;


import entity.Customer;
import entity.OrderDetails;
import entity.Orders;
import entity.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfig {

    private static FactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;
    private FactoryConfig(){
        Configuration configuration=new Configuration().configure().addAnnotatedClass(Orders.class).
                addAnnotatedClass(Stock.class).
                addAnnotatedClass(OrderDetails.class).addAnnotatedClass(Customer.class);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfig getInstance(){
        if (factoryConfig == null) {
            factoryConfig=new FactoryConfig();
        }return factoryConfig;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
