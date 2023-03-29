package dto.tableDTO;

import dto.StockDTO;
import entity.Stock;
import javafx.scene.control.Button;

public class OrderTableDTO {
    private String id;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btn;
    private StockDTO stock;


    public OrderTableDTO(String id, String description, int qty, double unitPrice, double total, Button btn, StockDTO stock) {
        this.id = id;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btn = btn;
        this.setStock(stock);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public StockDTO getStock() {
        return stock;
    }

    public void setStock(StockDTO stock) {
        this.stock = stock;
    }
}
