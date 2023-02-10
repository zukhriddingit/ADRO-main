package com.example.adro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private ScrollPane scrollPane_NewMovies;

    @FXML
    private ScrollPane scrollPane_TopMovies;

    @FXML
    private Pane dashboardPane;

    @FXML
    private Button cartButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button returnButton;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        returnButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml= FXMLLoader.load(getClass().getResource("DashboardPane.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dashboardPane.getChildren().removeAll();
                dashboardPane.getChildren().setAll(fxml);
            }
        });
        profileButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml= FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
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
                    fxml= FXMLLoader.load(getClass().getResource("CartPage.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dashboardPane.getChildren().removeAll();
                dashboardPane.getChildren().setAll(fxml);
            }
        });

        File file = new File("src/main/java/pictures");
        HBox hBox = new HBox(); // for scrollpane
        hBox.setAlignment(Pos.BASELINE_CENTER);

        try {
            hBox.getChildren().addAll(
                    createCustomNode("TOP GUN", "movie_1.jpeg", file.toURI().toURL().toString() + "movie_1.jpeg", 1),
                    createCustomNode("Matrix", "movie_3", file.toURI().toURL().toString() + "movie_3.jpeg", 2),
                    createCustomNode("Interstellar", "movie_4", file.toURI().toURL().toString() + "movie_4.jpg", 3),
                    createCustomNode("Inception", "movie_5", file.toURI().toURL().toString() + "movie_5.jpg", 4),
                    createCustomNode("The Dark Knight", "movie_6", file.toURI().toURL().toString() + "movie_6.jpg", 5),
                    createCustomNode("LUCY", "movie_7", file.toURI().toURL().toString() + "movie_7.jpg", 6),
                    createCustomNode("WEDNESDAY", "movie_8.jpg", file.toURI().toURL().toString() + "movie_8.jpg", 7),
                    createCustomNode("Forrest Gump", "movie_10", file.toURI().toURL().toString() + "movie_10.jpg", 8),
                    createCustomNode("Nope", "verMovie_2", file.toURI().toURL().toString() + "verMovie_2.jpeg", 9));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        scrollPane_TopMovies.setContent(hBox);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BASELINE_CENTER);
        try {
            hbox.getChildren().addAll(
                    createCustomNode("Puss in Boots", "new_movie1", file.toURI().toURL().toString() + "new_movie1.jpg", 10),
                    createCustomNode("Avatar II ", "new_movie2", file.toURI().toURL().toString() + "new_movie2.jpg", 11),
                    createCustomNode("High Heat", "new_movie3", file.toURI().toURL().toString() + "new_movie3.jpg", 12),
                    createCustomNode("Violent Night", "new_movie4", file.toURI().toURL().toString() + "new_movie4.jpg", 13),
                    createCustomNode("Troll", "new_movie5", file.toURI().toURL().toString() + "new_movie5.jpg", 14),
                    createCustomNode("Wakanda Forever", "new_movie6", file.toURI().toURL().toString() + "new_movie6.jpg", 15),
                    createCustomNode("Detective Knight", "new_movie7", file.toURI().toURL().toString() + "new_movie7.jpg", 16),
                    createCustomNode("The Woman King", "new_movie8", file.toURI().toURL().toString() + "new_movie8.jpg", 17),
                    createCustomNode("All Quiet on the Western Front", "movie_9", file.toURI().toURL().toString() + "movie_9.jpg", 18));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        scrollPane_NewMovies.setContent(hbox);
    }

    public Node createCustomNode(String movieName, String imageID , String imageLink, int movieID) {
        DataBaseConnect db = new DataBaseConnect();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(imageLink));
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
        imageView.setId(imageID);
        imageView.setOnMouseClicked(mouseEvent -> {
            try {
                myMethod(db.dashMovie(movieID),imageID);
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

    public void myMethod(AdminMovie adminMovie, String imageId){
        Parent fxml;
        File file = new File("src/main/java/pictures");
        try {
            AsilbeksVersionMyPageController as = new AsilbeksVersionMyPageController();
            as.setLabel(adminMovie.getTitle());
            as.setMovie_path(imageId);
            as.setGenre(adminMovie.getGenre());
            as.setDescription(adminMovie.getDescription());
            fxml = FXMLLoader.load(getClass().getResource("Asilbeks_Version_MoviePage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashboardPane.getChildren().removeAll();
        dashboardPane.getChildren().setAll(fxml);

    }
}




