package repo;

import org.hibernate.Session;

import java.util.List;

public interface SuperRepo <T,String>{
    String add(T obj, Session session);
    void update(T obj,Session session);
    void delete(T obj,Session session);
    String getLastId(Session sessions);
    List<T> getAll(Session session);
}
