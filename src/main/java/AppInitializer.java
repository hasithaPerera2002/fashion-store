import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FactoryConfig;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FactoryConfig.getInstance();
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainForm.fxml"))));
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        scene.getStylesheets().add(getClass().getResource("styleSheets/style.css").toExternalForm());
        primaryStage.show();



    }
}
