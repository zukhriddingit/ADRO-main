package com.example.adro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Editpage implements Initializable {
    @FXML
    private Button cancelBtn;
    @FXML
    private Pane pane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml= FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pane.getChildren().removeAll();
                pane.getChildren().addAll(fxml);

            }
        });

    }
}
