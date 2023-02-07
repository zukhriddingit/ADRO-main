package com.example.adro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    resultSet.getString("Movie_name"),
                    resultSet.getString("Movie_theatre"),
                    resultSet.getString("Movie_id"),
                    resultSet.getString("Language"),
                    resultSet.getDate("Movie_time"),
                    resultSet.getInt("Tickets_num"),
                    resultSet.getString("Price")
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

}
