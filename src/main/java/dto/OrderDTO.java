package dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

public class OrderDTO {
    private String id;
    private LocalDate date;
    private double total;

    public OrderDTO(String id, LocalDate date, double total) {
        this.setId(id);
        this.setDate(date);
        this.setTotal(total);
    }

    public OrderDTO(LocalDate date, double total) {
        this.setDate(date);
        this.setTotal(total);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
