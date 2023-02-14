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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String sql = "INSERT INTO `register`(`fullname`, `email`, `phone`, `username`, `password`, `dateOfBirth`) VALUES ('" + fullname.getText() + "','" + email.getText() + "','" + phoneNum.getText() + "','" + userName.getText() + "',MD5('" + password.getText() + "'),'" + dateOfBirth.getValue() + "')";
        if (fullname.getText().isEmpty()||email.getText().isEmpty()||phoneNum.getText().isEmpty()||userName.getText().isEmpty()||password.getText().isEmpty()||dateOfBirth.getValue().toString().isEmpty()){
            errorMsg.setText("Every field should be filled!");
        }else if (!emailValidate()){
            errorMsg.setText("Email should contain '@'");
        } else if (!phoneValidate(phoneNum.getText())){
            errorMsg.setText("Please, enter true form of phone number!");
        } else if (!birthDateValidate()) {
            errorMsg.setText("Minimum age required is 16!");
        } else if(dataBaseConnect.getInfo(userName.getText()).next()){
            errorMsg.setText("This username already exists!");
        }else {
            dataBaseConnect.insertData(sql);
            Node node = (Node)event.getSource();
            Stage dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Dashboard.fxml")), 1366, 700);
            dialogStage.setScene(scene);
            dialogStage.show();
            MyProfileController mp = new MyProfileController();
            mp.setUsername(userName.getText());
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

    private boolean emailValidate(){
        Pattern p = Pattern.compile("[a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)");
        Matcher m = p.matcher(email.getText());
        if (m.find() && m.group().equals(email.getText())){
            return true;
        }else {
            return false;
        }
    }

    private boolean phoneValidate(String phone){
        if (phone.length()==9){
            if (phone.startsWith("93")||phone.startsWith("91")||phone.startsWith("94")||phone.startsWith("97")||phone.startsWith("99")||phone.startsWith("88")||phone.startsWith("33")){
                for (int i=2;i<phone.length();i++){
                    if (!Character.isDigit(phone.charAt(i))) return false;
                }
                return true;
            }else return false;
        } else {
            return false;
        }
    }

    private boolean birthDateValidate(){
        LocalDate localDate = dateOfBirth.getValue();
        LocalDate currentPeriod = LocalDate.now();
        if (Period.between(localDate, currentPeriod).getYears()>=16){
            return true;
        }else{
            return false;
        }
    }
}