package repo.impl;

import entity.Customer;
import org.hibernate.Session;
import repo.CustomerRepo;

import java.util.List;

public class CustomerRepoImpl implements CustomerRepo {


    @Override
    public String add(Customer obj, Session session) {

        return (String) session.save(obj);
    }

    @Override
    public void update(Customer obj, Session session) {

    }

    @Override
    public void delete(Customer obj, Session session) {
        System.out.println("delete"+obj);

        session.delete(obj);
    }

    @Override
    public String getLastId(Session sessions) {
        return (String)sessions.createSQLQuery("SELECT id from  customer ORDER BY id DESC LIMIT 1").getSingleResult();

    }

    @Override
    public List<Customer> getAll(Session session) {
        System.out.println("repooo");
        return session.createCriteria(Customer.class).list();
    }
}

