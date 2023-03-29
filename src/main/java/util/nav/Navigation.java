package util.nav;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Navigation {
    private static Navigation navigation;

    AnchorPane pane;
    public void navigation(AnchorPane pane,Routs routs) throws IOException {
        this.pane=pane;
        switch (routs){
            case CUSTOMER:setUI("CustomerForm");
            break;
            case ITEM:setUI("ItemForm");
            break;
            case ORDER:setUI("OrderForm");
            break;
            case DASHBOARD:setUI("DashBoardForm");
        }
    }
    public static Navigation getInstance(){
        if(navigation==null)navigation=new Navigation();
        return navigation;
    }
    private void setUI(String s) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/"+s+".fxml")));

    }
}
