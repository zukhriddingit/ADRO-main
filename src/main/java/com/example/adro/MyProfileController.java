package com.example.adro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
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
    @FXML
    private Button LogoutBtn;
    @FXML
    private Button Editbtn;
    @FXML
    private Button Savebtn;

    @FXML
    private TextField TextfillEmail;

    @FXML
    private TextField TextfillFullname;

    @FXML
    private TextField TextfillNum;

    @FXML
    private TextField TextfillPassword;

    @FXML
    private TextField TextfillUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");
        try {
            Image image = new Image(file.toURI().toURL().toString()+"user.png");
            userImage.setImage(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        LogoutBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml= FXMLLoader.load(getClass().getResource("Login.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                dashboardPane.getChildren().removeAll();
//                dashboardPane.getChildren().setAll(fxml);
            }
        });
    }
}