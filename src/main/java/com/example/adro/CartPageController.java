package com.example.adro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CartPageController implements Initializable {

    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> nameCol;
    @FXML
    private TableColumn<Movie, String> theatreCol;
    @FXML
    private TableColumn<Movie, String> idCol;
    @FXML
    private TableColumn<Movie, String> languageCol;
    @FXML
    private TableColumn<Movie, String> timeCol;
    @FXML
    private TableColumn<Movie, String> ticketsCol;
    @FXML
    private TableColumn<Movie, String> priceCol;

    private static Date movieDate;

    private static ArrayList<Movie> movieList;



    @Override
    public void initialize(URL url, ResourceBundle rb){
        loadDate();
    }

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Movie movie = null;

    ObservableList<Movie> Movielist = FXCollections.observableArrayList();

    private void refreshable() throws SQLException {
        Movielist.clear();

        query = "SELECT * FROM `movies`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Movielist.add(new Movie(
                    resultSet.getString("title"),
                    resultSet.getInt("id"),
                    resultSet.getString("language"),
                    movieDate,
                    resultSet.getString("session"),
                    resultSet.getInt("price")
            ));
            movieTable.setItems(Movielist);
        }
    }

    private void loadDate() {
        connection = DataBaseConnect.getConnect();
        try {
            refreshable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        theatreCol.setCellValueFactory(new PropertyValueFactory<>("theatre"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        languageCol.setCellValueFactory(new PropertyValueFactory<>("language"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ticketsCol.setCellValueFactory(new PropertyValueFactory<>("tickets_num"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void deleteRow(ActionEvent event){
        movieTable.getItems().removeAll(movieTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void toClickPayment(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Payment_Click.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}