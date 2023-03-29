package util;

import dto.CustomerDTO;
import dto.OrderDTO;
import dto.StockDTO;
import dto.SuperDTO;
import entity.Customer;
import entity.Orders;
import entity.Stock;

import java.sql.Date;
import java.time.LocalDate;

public class Converter {
    private static Converter converter;

    private Converter() {
    }
    public static Converter getInstance(){
        if (converter == null) {
            converter=new Converter();
        }
        return converter;
    }
   public  Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTeleNo(), null);
   }
   public CustomerDTO toCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getTelNo());
   }
   public Stock toStock(StockDTO stockDTO){
        return new Stock(stockDTO.getId(), stockDTO.getName(), stockDTO.getQty(), stockDTO.getPrice(), stockDTO.getQty()* stockDTO.getPrice(), null);
   }

    public StockDTO toStockDTO(Stock s) {
        return new StockDTO(s.getId(),s.getName(),s.getQty(),s.getPrice());
    }

    public Orders toOrder(OrderDTO orderDTO) {
        return new Orders();
    }

}
