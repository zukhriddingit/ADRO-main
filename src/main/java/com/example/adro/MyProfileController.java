package com.example.adro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {
    @FXML
    private ImageView userImage;
    @FXML
    private Label FullName;

    @FXML
    private Label Email;

    @FXML
    private Label Number;

    @FXML
    private Label UsernName;

    @FXML
    private String dateOfBirth;
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
    private DatePicker DatePicker;


    @FXML
    private TextField TextfillUserName;
    @FXML
    private BorderPane border;
    @FXML
    private VBox text;

    @FXML
    private Pane pane;


    private static String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");

        try {
            Image image = new Image(file.toURI().toURL().toString()+"user.png");
            userImage.setImage(image);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

//        DataBaseConnect db = new DataBaseConnect();
//        try {
//            ResultSet r = db.getInfo(username);
//            r.next();
//            UsernName.setText(r.getString("username"));
//            FullName.setText(r.getString("fullname"));
//            Email.setText(r.getString("email"));
//            Number.setText(r.getString("phone"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Image image = new Image(file.toURI().toURL().toString()+"user.png");
//            userImage.setImage(image);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
        Editbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Parent fxml = null;
                try {
                    fxml= FXMLLoader.load(getClass().getResource("editpage.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pane.getChildren().removeAll();
                pane.getChildren().setAll(fxml);
            }
        });

    }


    public void signOut(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
}