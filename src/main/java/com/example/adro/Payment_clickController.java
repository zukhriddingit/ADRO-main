package com.example.adro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Payment_clickController {

    @FXML
    private Pane congratulations;
    @FXML
    private TableView<Movie> tableview_click;
    @FXML
    private TableColumn<Movie, String> name_col_click;
    @FXML
    private TableColumn<Movie, Integer> id_col_click;

    @FXML
    private TableColumn<Movie, Integer> price_col_click;
    @FXML
    private TableColumn<Movie, Integer> tickets_col_click;
    @FXML
    private Label total_click;
    @FXML
    private Button pay_click;

    private static int numberTickets;

//    @Override
//    public void initialize(URL url, ResourceBundle rb){
//        loadDate();
//    }
//
//    String query = null;
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    Movie movie = null;
//
//
//    ObservableList<Movie> Movielist = FXCollections.observableArrayList();
//
//    private void refreshable() throws SQLException {
//        Movielist.clear();
//
//        query = "SELECT Movie_name, Movie_id, Tickets_num, Price FROM cart";
//        preparedStatement = connection.prepareStatement(query);
//        resultSet = preparedStatement.executeQuery();
//
//        int total_price=0;
//        while (resultSet.next()) {
//            Movielist.add(new Movie(
//                    resultSet.getString("title"),
//                    resultSet.getInt("id"),
//                    numberTickets,
//                    resultSet.getInt("Price")
//            ));
//            total_price += resultSet.getInt("Price")*resultSet.getInt("Tickets_num");
//            tableview_click.setItems(Movielist);
//        }
//        total_click.setText("$"+String.valueOf(total_price));
//    }
//
//    private void loadDate() {
//        connection = DataBaseConnect.getConnect();
//        try {
//            refreshable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        name_col_click.setCellValueFactory(new PropertyValueFactory<>(""));
//        id_col_click.setCellValueFactory(new PropertyValueFactory<>("id"));
//        price_col_click.setCellValueFactory(new PropertyValueFactory<>("tickets_num"));
//        tickets_col_click.setCellValueFactory(new PropertyValueFactory<>("price"));
//    }

    @FXML
    private void congratulations(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("getpdf.fxml"));
            congratulations.getChildren().removeAll();
            congratulations.getChildren().setAll(fxml);
    }


}