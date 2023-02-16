package com.example.adro;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Editpage implements Initializable {
    @FXML
    private Button cancelBtn;

    @FXML
    private Button Savebtn;
    @FXML
    private Pane pane;
    @FXML
    private TextField TextfillEmail;

    @FXML
    private TextField TextfillFullname;

    @FXML
    private TextField TextfillNum;

    @FXML
    private javafx.scene.control.DatePicker DatePicker;


    @FXML
    private TextField TextfillUserName;

    public static void setUsername(String username) {
        Editpage.username = username;
    }

    private static String username;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataBaseConnect db = new DataBaseConnect();
        ResultSet r = null;
        try {
            r = db.getInfo(username);
            r.next();
            TextfillFullname.setText(r.getString("fullname"));
            TextfillEmail.setText(r.getString("email"));
            TextfillNum.setText(r.getString("phone"));
            TextfillUserName.setText(r.getString("username"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                Parent fxml = null;
//                try {
//                    fxml= FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                pane.getChildren().removeAll(fxml);
//                pane.getChildren().addAll(fxml);
//            }
//        });

    }

    public void saveBtn(ActionEvent event) throws SQLException {
        DataBaseConnect db = new DataBaseConnect();
        System.out.println(TextfillNum.getText());
        String sql = "UPDATE `register` SET fullname='"+TextfillFullname.getText()+"',dateOfBirth='"+DatePicker.getValue()+"',email='"+TextfillEmail.getText()+"',phone='"+TextfillNum.getText()+"',username='"+TextfillUserName.getText()+"' WHERE username = '"+username+"';";
        db.insertData(sql);



        Stage stage = (Stage) Savebtn.getScene().getWindow();
        stage.close();

    }
}
