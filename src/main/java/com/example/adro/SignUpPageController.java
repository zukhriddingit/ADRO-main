package com.example.adro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {

    private DataBaseConnect dataBaseConnect;

    @FXML
    private TextField fullname;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    @FXML
    private VBox imageVbox;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private Label errorMsg;


    @FXML
    protected void registerAction(ActionEvent event) throws SQLException, IOException {

        DataBaseConnect dataBaseConnect = new DataBaseConnect();
        String sql = "INSERT INTO `register`(`fullname`, `email`, `phone`, `username`, `password`, `dateOfBirth`) VALUES ('"+fullname.getText()+"','"+email.getText()+"','"+phoneNum.getText()+"','"+userName.getText()+"','"+password.getText()+"','"+dateOfBirth.getValue()+"')";
        if (!email.getText().contains("@")){
            errorMsg.setText("Email should contain '@'");
        }else if(dataBaseConnect.getInfo(userName.getText())){
            errorMsg.setText("This username already exists!");
        }else {
            dataBaseConnect.insertData(sql);
            Node node = (Node)event.getSource();
            Stage dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Dashboard.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }

    public void toSignIn(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");
        ImageView imageView =new ImageView();
        try {
            Image image = new Image(file.toURI().toURL().toString()+"black-panther-web.jpg");
            imageView.setFitHeight(500);
            imageView.setFitWidth(350);
            imageView.setImage(image);
            imageVbox.getChildren().add(imageView);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}