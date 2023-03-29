package service;

import service.impl.CustomerServiceImpl;
import service.impl.OrderDetailsServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.StockServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private final CustomerService customerService;
    private final StockService stockService;
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;

    private ServiceFactory(){

        customerService=new CustomerServiceImpl();
        stockService=new StockServiceImpl();
        orderService=new OrderServiceImpl();
        orderDetailsService=new OrderDetailsServiceImpl();
    }
    public static ServiceFactory getInstance(){
        if (serviceFactory == null) {
            serviceFactory=new ServiceFactory();
        }
        return serviceFactory;
    }
    public  <T> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes) {
            case CUSTOMER_SERVICE:
                return (T)  customerService;
            case STOCK_SERVICE:
                return (T) stockService;
            case ORDER_SERVICE:
                return (T) orderService;
            case ORDER_DETAILS_SERVICE:
                return (T) orderDetailsService;
        }
    return null;
    }

}
