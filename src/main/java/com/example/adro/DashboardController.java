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
//    ObservableList<AdminMovie> searchObservableList = FXCollections.observableArrayList();

    HashMap<String, String> images = new HashMap<String, String>() {{
        put("TOP GUN", "movie_1.jpeg");
        put("Matrix", "movie_3.jpeg");
        put("Interseller", "movie_4.jpg");
        put("Inception", "movie_5.jpg");
        put("The Dark Knight", "movie_6.jpg");
        put("LUCY", "movie_7.jpg");
        put("WEDNESDAY", "movie_8.jpg");
        put("FORREST GUMP", "movie_10.jpg");
        put("NOPE", "verMovie_2");
        put("PUSS IN BOOTS", "new_movie1");
        put("AVATAR2", "new_movie2");
        put("HIGH HEAT", "new_movie3");
        put("VIOLENT NIGHT", "new_movie4");
        put("TROLL", "new_movie5");
        put("WAKANDA FOREVER", "new_movie6");
        put("DETECTIVE KNIGHT", "new_movie7");
        put("THE WOMAN KING", "new_movie8");
        put("ALL QUIET ON THE WESTERN FRONT", "movie_9");
    }};

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
        profileButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
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

        File file = new File("src/main/java/pictures");
        HBox hBox = new HBox(); // for scrollpane
        hBox.setAlignment(Pos.BASELINE_CENTER);

        try {
            List<AdminMovie> movieList;
            String sql;
            if (Search.getText().isBlank()||Search.getText().isEmpty()||Search.getText()==null){
                sql = "SELECT * FROM `movies`";
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
                        createCustomNode(movieList.get(i).getTitle(), images.get(movieList.get(i).getTitle()), file.toURI().toURL().toString() + images.get(movieList.get(i).getTitle()), i));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        scrollPane_TopMovies.setContent(hBox);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BASELINE_CENTER);
        List<AdminMovie> movieList;
        String sql;
        sql = "SELECT * FROM `movies`";
        try {
            movieList = movies(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            for (int i = 0; i < movieList.size(); i++) {
                hbox.getChildren().addAll(
                        createCustomNode(movieList.get(i).getTitle(), images.get(movieList.get(i).getTitle()), file.toURI().toURL().toString() + images.get(movieList.get(i).getTitle()), i));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        scrollPane_NewMovies.setContent(hbox);
    }

    public Node createCustomNode(String movieName, String imageID, String imageLink, int movieID) {
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
}




