package repo.impl;

import entity.Stock;
import org.hibernate.Session;
import repo.StockRepo;

import java.util.List;

public class StockRepoImpl implements StockRepo {
    @Override
    public String add(Stock obj, Session session) {
        return (String) session.save(obj);
    }

    @Override
    public void update(Stock obj, Session session) {
        session.update(obj);
    }

    @Override
    public void delete(Stock obj, Session session) {
        session.delete(obj);
    }

    @Override
    public String getLastId(Session sessions) {
        return (String)sessions.createSQLQuery("SELECT id from  stock ORDER BY id DESC LIMIT 1").getSingleResult();
    }

    @Override
    public List<Stock> getAll(Session session) {
        List list = session.createCriteria(Stock.class).list();
        return list;
    }

    @Override
    public void updateInTransaction(int qty,String id, Session session) {
        session.createQuery("UPDATE stock set qty='"+qty+"' WHERE id='"+id+"'",Stock.class);
    }
}
