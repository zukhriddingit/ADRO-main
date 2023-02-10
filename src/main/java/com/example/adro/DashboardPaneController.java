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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DashboardPaneController implements Initializable {

    @FXML
    private ImageView MoviePageImg;

    @FXML
    private ImageView star;

    @FXML
    private Label MovieName;

    @FXML
    private Label Genre;

    @FXML
    private Label MovieDescription;

    @FXML
    private ScrollPane scrollPane_NewMovies2;

    @FXML
    private ScrollPane scrollPane_TopMovies2;


    @FXML
    private Pane dashboardPane;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        movie(scrollPane_TopMovies2,scrollPane_NewMovies2);
    }
    public void movie(ScrollPane scrollPane_TopMovies,ScrollPane scrollPane_NewMovies){

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

        // For 1 vertical column
        VBox vBox1 = new VBox();
//        Button button = new Button();
//        button.setMaxWidth(150);
//        button.setMaxHeight(210);
//        button.setGraphic(imageView);
//        button.setStyle("-fx-background-color: transparent;" + "-fx-cursor:hand;");
//
        Label label = new Label(movieName);
        label.setStyle("-fx-text-fill:white;" + "-fx-font-weight: 700;");
        vBox1.setSpacing(10);
        vBox1.getChildren().add(imageView);
//        vBox1.getChildren().add(button);
        vBox1.getChildren().add(label);
        vBox1.setStyle("-fx-padding: 5;");
        vBox1.setAlignment(Pos.CENTER);
//
//
//        //Giving onAction command to the movie  button
//
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Parent fxml;
//                try {
//                    fxml = FXMLLoader.load(getClass().getResource("Asilbeks_Version_MoviePage.fxml"));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                dashboardPane.getChildren().removeAll();
//                dashboardPane.getChildren().setAll(fxml);
//
//            }
//        });
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
