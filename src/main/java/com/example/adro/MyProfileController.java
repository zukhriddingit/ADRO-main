package com.example.adro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {
    @FXML
    private ImageView userImage;
    @FXML
    private Label FullName;

    @FXML
    private Label email;

    @FXML
    private Label number;

    @FXML
    private Label usernName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");
        try {
            Image image = new Image(file.toURI().toURL().toString()+"user.png");
            userImage.setImage(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}