package dto.tableDTO;

import javafx.scene.control.Button;

public class ItemTableDTO {
    private String id;
    private String name;
    private int qty;
    private double pricePerOne;
    private double total;
    private Button btn;

    public ItemTableDTO(String id, String name, int qty, double pricePerOne, double total, Button btn) {
        this.setId(id);
        this.setName(name);
        this.setQty(qty);
        this.setPricePerOne(pricePerOne);
        this.setTotal(total);
        this.setBtn(btn);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(double pricePerOne) {
        this.pricePerOne = pricePerOne;
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
}
