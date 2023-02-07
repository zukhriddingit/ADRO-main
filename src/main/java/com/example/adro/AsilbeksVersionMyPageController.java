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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");
        try {
            Image image = new Image(file.toURI().toURL().toString()+"sta.jpg");
            Image image1 = new Image(file.toURI().toURL().toString()+"movie_1.jpeg");
            MoviePageImg.setImage(image1);
            star.setImage(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }
}