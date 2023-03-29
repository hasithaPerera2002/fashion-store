package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.tableDTO.CustomerTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.CustomerService;
import service.ServiceFactory;
import service.ServiceTypes;
import util.Regex;
import util.TextFields;

import java.util.Optional;

public class CustomerFormController {
    public TableView<CustomerTableDTO> tblCustomer;
    public TableColumn<CustomerTableDTO, String> colDelete;
    public JFXButton btnSave;
    public JFXButton btnNew;
    private CustomerService customerService;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<CustomerTableDTO, String> colId;

    @FXML
    private TableColumn<CustomerTableDTO, String> colName;

    @FXML
    private TableColumn<CustomerTableDTO, String> colAddress;

    @FXML
    private TableColumn<CustomerTableDTO, String> colContact;

    @FXML
    private Label custId;

    @FXML
    private JFXTextField custName;

    @FXML
    private JFXTextField custAddress;

    @FXML
    private JFXTextField custTele;

    public void initialize() {
        customerService = ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER_SERVICE);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("tele"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        btnNew.setDisable(true);
        setTableData();
        setId();
    }

    private void setTableData() {
        ObservableList<CustomerDTO> all = customerService.getAll();
        ObservableList<CustomerTableDTO> observableList = FXCollections.observableArrayList();
        for (CustomerDTO c : all
        ) {
            Button button = new Button("DELETE");
            button.setStyle("-fx-background-color: rgb(230, 57, 70); ");
            button.setOnAction(actionEvent -> {
                        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "DO YOU WANT TO DELETE THIS", ButtonType.YES, ButtonType.NO).showAndWait();
                        if ((buttonType.get().equals(ButtonType.YES))) {
                            try {
                                customerService.delete(c);
                                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
                                setTableData();
                                setId();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
            );
            observableList.add(new CustomerTableDTO(c.getId(), c.getName(), c.getAddress(), c.getTeleNo(), button));
        }
        tblCustomer.setItems(observableList);

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFields.ADDRESS,custAddress)) {
            new Alert(Alert.AlertType.ERROR,"ENTER VALID ADDRESS").show();
            return;
        }
        if (!Regex.setTextColor(TextFields.NAME,custName)){
            new Alert(Alert.AlertType.ERROR,"ENTER VALID NAME").show();
            return;

        }
        if (!Regex.setTextColor(TextFields.PHONE,custTele)){
            new Alert(Alert.AlertType.ERROR,"ENTER VALID CONTACT NUMBER").show();
            return;

        }
        if (btnSave.getText().equals("SAVE")) {
            if (0 < customerService.add(
                    new CustomerDTO(custId.getText(), custName.getText(), custAddress.getText(), custTele.getText())
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added").show();
                setTableData();
                clear();
            } else {
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Customer not  Added").show();
            }
        } else {
            if (0 < customerService.update(new CustomerDTO(custId.getText(), custName.getText(), custAddress.getText(), custTele.getText()))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                setTableData();
                clear();
            } else {
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Error6").show();
            }
        }

    }

    public void setId() {
        custId.setText(customerService.getNewId());
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        tblCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                          @Override
                                          public void handle(MouseEvent mouseEvent) {
                                              CustomerTableDTO c = tblCustomer.getSelectionModel().getSelectedItem();
                                              custId.setText(c.getId());
                                              custName.setText(c.getName());
                                              custAddress.setText(c.getAddress());
                                              custTele.setText(String.valueOf(c.getTele()));
                                              btnSave.setText("UPDATE");
                                              btnNew.setDisable(false);
                                          }
                                      }
        );
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        btnNew.setDisable(true);
        btnSave.setText("SAVE");
        clear();
    }

    private void clear() {
        custName.clear();
        custAddress.clear();
        custId.setText(customerService.getNewId());
        custTele.clear();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
