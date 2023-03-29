package repo;

import entity.Stock;
import org.hibernate.Session;

public interface StockRepo extends SuperRepo<Stock,String>{
    void updateInTransaction(int qty,String id, Session session);
}
