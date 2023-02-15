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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public String getSearch() {
        return Search;
    }

    public void setSearch(String search) {
        this.Search = search;
    }

    private static String Search;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        movie(scrollPane_TopMovies2,scrollPane_NewMovies2);
    }
    public void movie(ScrollPane scrollPane_TopMovies,ScrollPane scrollPane_NewMovies){

        DashboardController dash = new DashboardController();
        HashMap<String, String> images = dash.images;

        File file = new File("src/main/java/pictures");
        HBox hBox = new HBox(); // for scrollpane
        hBox.setAlignment(Pos.BASELINE_CENTER);

        try {
            List<AdminMovie> movieList;
            String sql;
            if (Search.isEmpty()||Search==null){
                sql = "SELECT * FROM `movies` ORDER BY release_year DESC LIMIT 5;";
            }else {
                sql = "SELECT * FROM `movies` WHERE title LIKE '%"+Search+"%' OR genre LIKE '%"+Search+"%' OR language LIKE '%"+Search+"%'";
            }
            try {
                movieList = movies(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < movieList.size(); i++) {
                hBox.getChildren().addAll(
                        createCustomNode(movieList.get(i).getTitle(), movieList.get(i).getImage_path(), file.toURI().toURL().toString() +movieList.get(i).getImage_path(), movieList.get(i).getTitle()));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        scrollPane_TopMovies.setContent(hBox);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BASELINE_CENTER);
        List<AdminMovie> movieList;
        String sql;
        sql = "SELECT * FROM `movies` ORDER BY imdb DESC LIMIT 5;";
        try {
            movieList = movies(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            for (int i = 0; i < movieList.size(); i++) {
                hbox.getChildren().addAll(
                        createCustomNode(movieList.get(i).getTitle(), movieList.get(i).getImage_path(), file.toURI().toURL().toString() +movieList.get(i).getImage_path(), movieList.get(i).getTitle()));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        scrollPane_NewMovies.setContent(hbox);
    }

    public Node createCustomNode(String movieName, String imageID , String imageLink, String movieID) {
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
            adminMovie.setTitle(resultSet.getString("title"));
            adminMovie.setImage_path(resultSet.getString("image_path"));
            result.add(adminMovie);
        }
        return result;
    }
}
