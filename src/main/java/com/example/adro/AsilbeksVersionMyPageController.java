package com.example.adro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;



public class AsilbeksVersionMyPageController implements Initializable{


    @FXML
    private ImageView MoviePageImg;

    @FXML
    private ImageView star;

    public static String movie_path, label, description, genre;

    @FXML
    private Label MovieName;

    @FXML
    private Label Genre;

    @FXML
    private Label MovieDescription;

    public String getMovie_path() {
        return movie_path;
    }

    public void setMovie_path(String movie_path) {
        this.movie_path = movie_path;
    }

    public AsilbeksVersionMyPageController(String movie_path, String label, String description, String genre) {
        this.movie_path = movie_path;
        this.label = label;
        this.description = description;
        this.genre = genre;
    }

    public AsilbeksVersionMyPageController(){}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");
        try {
            Image image = new Image(file.toURI().toURL().toString()+"sta.jpg");
            Image image1 = new Image(file.toURI().toURL().toString()+movie_path);
            System.out.println(movie_path);
            MoviePageImg.setImage(image1);
            star.setImage(image);
            MovieName.setText(label);
            Genre.setText(genre);
            MovieDescription.setText(description);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }
}