package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StockDTO;
import dto.tableDTO.ItemTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.ServiceTypes;
import service.StockService;
import util.Regex;
import util.TextFields;

import java.util.Optional;

public class ItemFormController {
    public JFXButton btnSave;
    public TableColumn<ItemTableDTO, String> colDelete;
    public JFXButton btnNew;
    public JFXButton btnClear;
    ObservableList<ItemTableDTO> observableList;
    private StockService stockService;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<ItemTableDTO> tblItem;
    @FXML
    private TableColumn<ItemTableDTO, String> colId;
    @FXML
    private TableColumn<ItemTableDTO, String> colName;
    @FXML
    private TableColumn<ItemTableDTO, String> colQty;
    @FXML
    private TableColumn<ItemTableDTO, String> colPrice;
    @FXML
    private TableColumn<ItemTableDTO, String> colTotal;
    @FXML
    private Label ItemId;
    @FXML
    private JFXTextField itemName;
    @FXML
    private JFXTextField itemQty;
    @FXML
    private JFXTextField price;

    public void initialize() {
        btnNew.setDisable(true);
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerOne"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        stockService = ServiceFactory.getInstance().getService(ServiceTypes.STOCK_SERVICE);
        btnNew.setDisable(true);
        setTableData();
        setId();
    }

    private void setTableData() {
        System.out.println("setTable called");
        ObservableList<StockDTO> all = stockService.getAll();
        observableList = FXCollections.observableArrayList();
        for (StockDTO s : all
        ) {
            System.out.println(s.getId());
            Button button = new Button("DELETE");
            button.setStyle("-fx-background-color: rgb(230, 57, 70); ");
            button.setOnAction(actionEvent -> {
                        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "DO YOU WANT TO DELETE THIS", ButtonType.YES, ButtonType.NO).showAndWait();
                        if ((buttonType.get().equals(ButtonType.YES))) {
                            try {
                                stockService.delete(s);
                                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
                                setTableData();
                                setId();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
            );
            observableList.add(new ItemTableDTO(s.getId(), s.getName(), s.getQty(), s.getPrice(), s.getPrice() * s.getQty(), button));
        }
        tblItem.setItems(observableList);
    }

    private void setId() {
        ItemId.setText(stockService.getNewId());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!Regex.setTextColor(TextFields.NAME,itemName)) {
            new Alert(Alert.AlertType.ERROR,"ENTER VALID NAME").show();
            return;
        }
        if (!Regex.setTextColor(TextFields.INTEGER,itemQty)) {
            new Alert(Alert.AlertType.ERROR,"ENTER VALID QUANTITY").show();
            return;
        } if (!Regex.setTextColor(TextFields.DOUBLE,price)) {
            new Alert(Alert.AlertType.ERROR,"ENTER VALID VALUE").show();
            return;
        }
        if (btnSave.getText().equals("SAVE")) {
            if (0 < stockService.add(
                    new StockDTO(ItemId.getText(), itemName.getText(), Integer.parseInt(itemQty.getText()),
                            Double.parseDouble(price.getText()))
            )) {
                clear();
                setTableData();
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added").show();
            } else {
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Item not  Added").show();
            }
        } else {
            if (0 < stockService.update(new StockDTO(ItemId.getText(), itemName.getText(), Integer.parseInt(itemQty.getText()),
                    Double.parseDouble(price.getText())))) {
                new Alert(Alert.AlertType.CONFIRMATION, "SAVED").show();
                btnSave.setText("SAVE");
                setTableData();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Item not  Added").show();
                btnSave.setText("SAVE");
            }
        }

    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        clear();
        btnSave.setText("SAVE");
        btnNew.setDisable(true);

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    private void clear() {
        itemName.clear();
        price.clear();
        itemQty.clear();
        ItemId.setText(stockService.getNewId());
    }

    public void tblOnClick(MouseEvent mouseEvent) {
        tblItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ItemTableDTO selectedItem = tblItem.getSelectionModel().getSelectedItem();
                ItemId.setText(selectedItem.getId());
                itemQty.setText(String.valueOf(selectedItem.getQty()));
                price.setText(String.valueOf(selectedItem.getPricePerOne()));
                itemName.setText(selectedItem.getName());
            }

        });
        btnSave.setText("UPDATE");
        btnNew.setDisable(false);
    }
}
