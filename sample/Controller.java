package sample;

import static sample.Main.userid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    Connection conn = new DataBase().getConn();

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> rolecb;
    ObservableList<String> rolelist = FXCollections.observableArrayList();

    public Controller() throws SQLException, ClassNotFoundException {
    }

    @FXML
    public void input() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("");
        try {
            switch (rolecb.getValue().trim()) {
                case "Administrator":
                    preparedStatement = conn.prepareStatement("select ID from Administrator where Login = ? AND Password = ?");
                    break;
                case "Manager":
                    preparedStatement = conn.prepareStatement("select ID from Manager where Login = ? AND Password = ?");
                    break;
                case "Accountant":
                    preparedStatement = conn.prepareStatement("select ID from Accountant where Login = ? AND Password = ?");
                    break;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(("Некорректно заполнены поля"));
        }

        try {
            preparedStatement.setString(1, login.getText().trim());
            preparedStatement.setString(2, password.getText().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userid = resultSet.getInt(1);
                Stage primaryStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource(rolecb.getValue().trim() + ".fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                primaryStage.setTitle("Мебельная компания МИФ");
                if (rolecb.getValue().trim().equals("Accountant")) {
                    primaryStage.setScene(new Scene(root, 428, 213));
                } else {
                    primaryStage.setScene(new Scene(root, 600, 400));
                }
                primaryStage.show();
                password.getScene().getWindow().hide();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(("Некорректно заполнены поля"));
            alert.showAndWait();
        }
    }

    @FXML
    public void mainscreen() {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainscreen.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 535, 477));
        primaryStage.show();
        password.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() {
        rolelist.add("Administrator");
        rolelist.add("Manager");
        rolelist.add("Accountant");
        rolecb.setItems(rolelist);

    }

}
