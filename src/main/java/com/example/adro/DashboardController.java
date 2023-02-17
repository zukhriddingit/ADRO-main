package com.example.adro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DashboardController implements Initializable {
    @FXML
    private ScrollPane scrollPane_NewMovies;

    @FXML
    private ScrollPane scrollPane_TopMovies;

    @FXML
    private Pane dashboardPane;
    @FXML
    private Button about;

    @FXML
    private Button cartButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField Search;


    public TextField getSearch() {
        return Search;
    }

    public void setSearch(TextField search) {
        Search = search;
    }

    public void setDashboardPane(Pane dashboardPane) {
        this.dashboardPane = dashboardPane;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        returnButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                Search.setText("");
                DashboardPaneController dcc = new DashboardPaneController();
                dcc.setSearch(Search.getText());
                try {
                    fxml = FXMLLoader.load(getClass().getResource("DashboardPane.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dashboardPane.getChildren().removeAll();
                dashboardPane.getChildren().setAll(fxml);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dashboardPane.getChildren().removeAll();
                dashboardPane.getChildren().setAll(fxml);
            }
        });
        about.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dashboardPane.getChildren().removeAll();
                dashboardPane.getChildren().setAll(fxml);
            }
        });

        DashboardPaneController dbc = new DashboardPaneController();
        dbc.movie(scrollPane_TopMovies, scrollPane_NewMovies);
    }

    public Node createCustomNode(String movieName, String imageID, String imageLink, String movieID) {
        DataBaseConnect db = new DataBaseConnect();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(imageLink));
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
        imageView.setId(imageID);
        imageView.setOnMouseClicked(mouseEvent -> {
            try {
                myMethod(db.dashMovie(movieID), imageID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        VBox vBox1 = new VBox();
        Label label = new Label(movieName);
        label.setStyle("-fx-text-fill:white;" + "-fx-font-weight: 700;");
        vBox1.setSpacing(10);
        vBox1.getChildren().add(imageView);
        vBox1.getChildren().add(label);
        vBox1.setStyle("-fx-padding: 5;");
        vBox1.setAlignment(Pos.CENTER);
        return vBox1;
    }

    public void myMethod(AdminMovie adminMovie, String imageId) {
        Parent fxml;
        File file = new File("src/main/java/pictures");
        try {
            AsilbeksVersionMyPageController as = new AsilbeksVersionMyPageController();
            as.setLabel(adminMovie.getTitle());
            as.setMovie_path(imageId);
            as.setGenre(adminMovie.getGenre());
            as.setDescription(adminMovie.getDescription());
            as.setRelese(adminMovie.getYearRelease());
            as.setiMDB(adminMovie.getImdb());
            as.setLang(adminMovie.getLanguage());
            fxml = FXMLLoader.load(getClass().getResource("Asilbeks_Version_MoviePage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashboardPane.getChildren().removeAll();
        dashboardPane.getChildren().setAll(fxml);

    }

    public List<AdminMovie> movies(String sql) throws SQLException {
        DataBaseConnect db = new DataBaseConnect();
        List<AdminMovie> result = new ArrayList<>();
        ResultSet resultSet = db.getMovies(sql);
        while (resultSet.next()) {
            AdminMovie adminMovie = new AdminMovie();
            adminMovie.setStartDate(resultSet.getDate("start_date"));
            adminMovie.setEndDate(resultSet.getDate("end_date"));
            adminMovie.setSession(resultSet.getString("session"));
            adminMovie.setTitle(resultSet.getString("title"));
            adminMovie.setLanguage(resultSet.getString("language"));
            adminMovie.setPrice(resultSet.getInt("price"));
            adminMovie.setGenre(resultSet.getString("genre"));
            adminMovie.setDescription(resultSet.getString("description"));
            adminMovie.setNumberTickets(resultSet.getInt("number_tickets"));
            adminMovie.setImdb(resultSet.getFloat("imdb"));
            adminMovie.setYearRelease(resultSet.getInt("release_year"));
            result.add(adminMovie);
        }
        return result;
    }

    public void searchAction(ActionEvent event) {
        Parent fxml;
        try {
            DashboardPaneController dc = new DashboardPaneController();
            String val = Search.getText();
            dc.setSearch(Search.getText());
            fxml = FXMLLoader.load(getClass().getResource("DashboardPane.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashboardPane.getChildren().removeAll();
        dashboardPane.getChildren().setAll(fxml);
    }

    public void profileAction(ActionEvent event) {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashboardPane.getChildren().removeAll();
        dashboardPane.getChildren().setAll(fxml);
    }

    public void centerRemove(Parent fxml){
        dashboardPane.getChildren().setAll(fxml);
    }
}




