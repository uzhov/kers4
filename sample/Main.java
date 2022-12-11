package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static int userid;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainscreen.fxml"));
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 535, 477));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
