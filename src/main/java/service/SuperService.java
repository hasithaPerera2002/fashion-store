package service;

import javafx.collections.ObservableList;

import javax.persistence.NoResultException;
import java.util.List;

public interface SuperService<T,String> {
    int add(T obj) ;
    int delete(T obj);
    int update(T obj);
    String getLastId() throws  NoResultException;
    String getNewId();
    ObservableList <T>getAll();

}
