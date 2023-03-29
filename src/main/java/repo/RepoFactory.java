package repo;

import org.hibernate.criterion.Order;
import repo.impl.CustomerRepoImpl;
import repo.impl.OrderDetailsRepoImpl;
import repo.impl.OrderRepoImpl;
import repo.impl.StockRepoImpl;

public class RepoFactory {
    private static RepoFactory repoFactory;
    private final CustomerRepo customerRepo;
    private final StockRepo stockRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    public RepoFactory() {
        customerRepo=new CustomerRepoImpl();
        stockRepo=new StockRepoImpl();
        orderRepo=new OrderRepoImpl() ;
        orderDetailsRepo=new OrderDetailsRepoImpl();

    }
    public static RepoFactory getInstance(){
        if (repoFactory == null) {
            repoFactory=new RepoFactory();
        }
        return repoFactory;
    }
    public <T>T getRepo(RepoTypes repoTypes){
        switch (repoTypes){
            case CUSTOMER_REPO:return (T) customerRepo;
            case STOCK_REPO:return (T) stockRepo;
            case ORDER_REPO:return (T) orderRepo;
            case ORDER_DETAILS:return (T)orderDetailsRepo;
        }
        return null;
    }
}
