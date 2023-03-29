package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.StockDTO;
import dto.tableDTO.OrderTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import service.*;
import util.Converter;
import util.Regex;
import util.TextFields;

import javax.xml.stream.events.DTD;
import java.time.LocalDate;
import java.util.Objects;

public class OrderFormController {
    StockService stockService;
    CustomerService customerService;
    OrderService orderService;
    private ObservableList<OrderTableDTO> tblOrder = FXCollections.observableArrayList();
    ObservableList<StockDTO> stockDTOS=FXCollections.observableArrayList();
    @FXML
    private Label lblOrderId;


    @FXML
    private JFXComboBox<CustomerDTO> cmbCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox<StockDTO> cmbItemCode;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<OrderTableDTO> tblOrderCart;

    @FXML
    private TableColumn<OrderTableDTO,String> colItemCode;

    @FXML
    private TableColumn<OrderTableDTO, String> colDescription;

    @FXML
    private TableColumn<OrderTableDTO, Integer> colQty;

    @FXML
    private TableColumn<OrderTableDTO, Double> colUnitPrice;

    @FXML
    private TableColumn<OrderTableDTO, Double> colTotal;

    @FXML
    private TableColumn<OrderTableDTO, Button> colAction;

    @FXML
    private Label lblTotal;

    public void initialize() {

        stockService = ServiceFactory.getInstance().getService(ServiceTypes.STOCK_SERVICE);
        customerService = ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER_SERVICE);
        orderService=ServiceFactory.getInstance().getService(ServiceTypes.ORDER_SERVICE);

        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        setData();
        txtQty.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtQty.setText(newValue.replaceAll("\\D", "")); // force the text field to be numeric only
            }
        });
        cmbCustomerId.setConverter(new StringConverter<CustomerDTO>() {
            @Override
            public String toString(CustomerDTO customerDTO) {
                if (customerDTO == null) return null;
                return customerDTO.getId();
            }

            @Override
            public CustomerDTO fromString(String s) {
                return null;
            }
        });
        cmbItemCode.setConverter(new StringConverter<StockDTO>() {
            @Override
            public String toString(StockDTO stockDTO) {
                if (stockDTO == null) return null;
                return stockDTO.getId();
            }

            @Override
            public StockDTO fromString(String s) {
                return null;
            }
        });


        //setData();
    }

    private void setData() {
       stockDTOS = stockService.getAll();
        cmbItemCode.setItems(stockDTOS);
        cmbCustomerId.setItems(customerService.getAll());
        lblOrderId.setText(orderService.getNewId());
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFields.INTEGER, (JFXTextField) txtQty)) {
            new Alert(Alert.AlertType.ERROR,"ENTER VALID QUANTITY").show();
            return;
        }
       /* if (cmbItemCode.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }*/
        if (cmbCustomerId.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }
        if (tblOrder == null) {
            new Alert(Alert.AlertType.ERROR,"ADD ITEMS TO PACE ORDER").show();
            return;
        }
        int i = Integer.parseInt(txtQty.getText());

        Button btn = new Button("REMOVE");
        btn.setStyle("-fx-background-color: #ff0000");
        try {
            if (!(cmbItemCode.getSelectionModel().getSelectedItem().getQty() - i >= 0)) {
                new Alert(Alert.AlertType.ERROR, "No Available QTY").show();
                return;

            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Enter valid quantity").show();
            setBeginingValues();
            return;
        }

        if (checkAlreadyInCart(cmbItemCode.getSelectionModel().getSelectedItem(), txtQty.getText())) {
            setBeginingValues();
            tblOrderCart.refresh();
            return;
        }
        String id = cmbItemCode.getSelectionModel().getSelectedItem().getId();
        int qty = Integer.parseInt(txtQty.getText());
        OrderTableDTO orderTableTO = new OrderTableDTO(id,cmbItemCode.getSelectionModel().getSelectedItem().getName(),
                qty, cmbItemCode.getSelectionModel().getSelectedItem().getPrice(),
                cmbItemCode.getSelectionModel().getSelectedItem().getPrice()*qty,
                btn,cmbItemCode.getSelectionModel().getSelectedItem());
        btn.setOnAction(actionEvent -> {
            boolean remove = tblOrder.remove(orderTableTO);
            if (remove) new Alert(Alert.AlertType.CONFIRMATION, "REMOVED").show();
            tblOrderCart.setItems(tblOrder);
            for (StockDTO o : stockDTOS
            ) {
                if (Objects.equals(o.getId(), id)) {
                    o.setQty(o.getQty() + qty);
                    setBeginingValues();
                    setTotal();
                }
            }


        });
        loadTableData(orderTableTO);
        cmbItemCode.getSelectionModel().getSelectedItem().setQty(
                cmbItemCode.getSelectionModel().getSelectedItem().getQty() - Integer.parseInt(txtQty.getText()));
        setBeginingValues();
        setTotal();
    }

    private void loadTableData(OrderTableDTO orderTableTO) {
        tblOrder.add(orderTableTO);
        tblOrderCart.setItems(tblOrder);
    }

    private boolean checkAlreadyInCart(StockDTO selectedItem, String text) {
        int i = Integer.parseInt(text);
        for (OrderTableDTO o : tblOrder
        ) {
            if (o.getId().equals(selectedItem.getId())) {
                selectedItem.setQty(selectedItem.getQty() - i);
                o.setQty(o.getQty() + i);
                o.setTotal((o.getUnitPrice() * i)+o.getTotal());
                tblOrderCart.setItems(tblOrder);
                setTotal();
                return true;
            }
        }
        return false;
    }

    private void setBeginingValues() {
        cmbItemCode.getSelectionModel().select(null);
        txtQty.clear();
        lblTotal.setText("");
        lblName.setText("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");

    }

    private double setTotal() {
        double total = 0.0;
        for (OrderTableDTO to : tblOrder
        ) {
            total += to.getTotal();
        }
        lblTotal.setText((String.valueOf(total)));
        return total;
    }



    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        //orderService.add(new OrderDTO(lblOrderId.getText(), LocalDate.now(),setTotal()));
        orderService.add(tblOrder, Converter.getInstance().toCustomer(cmbCustomerId.getSelectionModel().getSelectedItem()), setTotal(),lblOrderId.getText() );


    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        if (cmbCustomerId.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        CustomerDTO selectedItem = cmbCustomerId.getSelectionModel().getSelectedItem();
        lblCustomerName.setText(selectedItem.getName());
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        if (cmbItemCode.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        StockDTO selectedItem = cmbItemCode.getSelectionModel().getSelectedItem();
        lblName.setText(selectedItem.getName());
        lblQtyOnHand.setText((String.valueOf(selectedItem.getQty())));
        lblUnitPrice.setText(String.valueOf(selectedItem.getPrice()));
    }

}
