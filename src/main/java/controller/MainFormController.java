package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.nav.Navigation;
import util.nav.Routs;

import java.io.IOException;
import java.time.LocalDate;

public class MainFormController {

    @FXML
    private AnchorPane menuContext;

    @FXML
    private Label lblUser;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnCustomerManagement;

    @FXML
    private JFXButton btnStockManagement;

    @FXML
    private JFXButton btnUserManagement;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnDashBoard;

    @FXML
    private AnchorPane mainContext;

    @FXML
    private Label lblProfit;

    @FXML
    private Label lblMostSell;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;
    @FXML

    public void initialize() throws IOException {
        Navigation.getInstance().navigation(mainContext,Routs.DASHBOARD);
        setDate();
    }

    private void setDate() {
        lblDate.setText(LocalDate.now().toString());
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(mainContext, Routs.CUSTOMER);

    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(mainContext,Routs.DASHBOARD);
    }



    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(mainContext,Routs.ORDER);
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        Navigation.getInstance().navigation(mainContext,Routs.ITEM);
    }

}
