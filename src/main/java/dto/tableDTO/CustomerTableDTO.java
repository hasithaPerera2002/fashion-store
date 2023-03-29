package dto.tableDTO;

import javafx.scene.control.Button;

public class CustomerTableDTO {
    private String id;
    private String name;
    private String address;
    private String tele;
    private Button btn;

    public CustomerTableDTO(String id, String name, String address, String tele, Button btn) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setTele(tele);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
