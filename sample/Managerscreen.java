package sample;

import static sample.Main.userid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Managerscreen {

    Connection conn = new DataBase().getConn();

    @FXML
    private ComboBox<String> buyercb_m;

    ObservableList<String> buyerlist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ManagerPOJO, String> datacl_m;


    @FXML
    private ComboBox<String> deliverymancb;

    ObservableList<String> deliverymanlist = FXCollections.observableArrayList();

    @FXML
    private Button desingbt;

    @FXML
    private Button mainscreenbt;

    @FXML
    private TableView<ManagerPOJO> managertv;

    ObservableList<ManagerPOJO> orderlist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ManagerPOJO, Integer> numbercl_m;

    public Managerscreen() throws SQLException, ClassNotFoundException {
    }

    @FXML
    public  void initialize() throws SQLException {

        numbercl_m.setCellValueFactory(new PropertyValueFactory<>("id"));
        datacl_m.setCellValueFactory(new PropertyValueFactory<>("date"));

        update();

        desingbt.setOnAction(e ->{
            if (managertv.getSelectionModel().getSelectedItem() != null){
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = conn.prepareStatement("update Orders set Buyer_ID = " + buyercb_m.getValue() + ", Deliveryman_ID =" + deliverymancb.getValue() + " where ID = " + managertv.getSelectionModel().getSelectedItem().getId());
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        mainscreenbt.setOnAction(e -> {
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
            mainscreenbt.getScene().getWindow().hide();
        });

        PreparedStatement preparedStatement = conn.prepareStatement("select * from Buyer where Manager_ID = " + userid);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            buyerlist.add(String.valueOf(resultSet.getInt(1)));
        }
        buyercb_m.setItems(buyerlist);

        preparedStatement = conn.prepareStatement("select * from Deliveryman where Manager_ID = " + userid);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            deliverymanlist.add(String.valueOf(resultSet.getInt(1)));
        }
        deliverymancb.setItems(deliverymanlist);

    }

    public void update() throws SQLException {
        orderlist.clear();
        PreparedStatement preparedStatement = conn.prepareStatement("select * from Orders where Buyer_ID = 0 or Deliveryman_ID = 0");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            orderlist.add(new ManagerPOJO(resultSet.getInt("ID"), resultSet.getString("Data_order")));
            System.out.println("vgethhtr");
        }
        managertv.setItems(orderlist);
    }

}
