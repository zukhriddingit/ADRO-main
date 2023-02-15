package com.example.adro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutUsController implements Initializable {

    @FXML
    private Button imgBtn;
    @FXML
    private ImageView image;
    @FXML
    private ImageView qr_ins;

    @FXML
    private ImageView qr_tel;

    @FXML
    private ImageView qr_twit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File file = new File("src/main/java/pictures");
        try {
            Image image1 = new Image(file.toURI().toURL().toString() + "adro-removebg-preview.png");
            Image tg = new Image(file.toURI().toURL().toString() + "tg.jpg");
            Image insta = new Image(file.toURI().toURL().toString() + "qr_Instagram.png");
            Image twit = new Image(file.toURI().toURL().toString() + "qr_Twitter.png");

            qr_ins.setImage(insta);
            qr_tel.setImage(tg);
            qr_twit.setImage(twit);
            image.setImage(image1);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
