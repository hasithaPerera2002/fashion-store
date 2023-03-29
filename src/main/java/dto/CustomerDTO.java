package dto;

public class CustomerDTO implements SuperDTO {
    private String id;
    private String name;
    private String address;
    private String teleNo;

    public CustomerDTO(String id, String name, String address, String teleNo) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setTeleNo(teleNo);
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

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        this.teleNo = teleNo;
    }
}
