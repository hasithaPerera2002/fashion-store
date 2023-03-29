package controller;

import dto.OrderDTO;
import dto.StockDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.OrderService;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StockService;

import java.time.LocalDate;
import java.util.Date;

public class DashBoardFormController {
    private AnchorPane anchorPane;

    @FXML
    private LineChart<String,Integer > chartSales;

    @FXML
    private Label lblProfit;

    @FXML
    private Label lblMostSell;

    @FXML
    private Label lblSales;
    OrderService orderService;
   /* public void initialize(){

        orderService=ServiceFactory.getInstance().getService(ServiceTypes.ORDER_SERVICE);
        CategoryAxis xAxis=new CategoryAxis();
        CategoryAxis yAxis = new CategoryAxis();
        chartSales= new LineChart<String, Integer>(xAxis, yAxis);
        chartSales.setTitle("SALES ");
        XYChart.Series <String,Integer>series=new XYChart.Series();
       ObservableList<OrderDTO>observableList= orderService.getChartData();
        for (OrderDTO o:observableList
             ) {
            series.getData().add(o.getDate().getMonth().getValue(),o.getTotal().intValue);
        }
        chartSales.getData().add(series);

    }*/
}
