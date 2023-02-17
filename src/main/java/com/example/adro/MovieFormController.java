package com.example.adro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MovieFormController implements Initializable {

    @FXML
    private Button addMovieBtn;

    @FXML
    private ComboBox<String> combo_genre;

    @FXML
    private ComboBox<String> combo_languages;

    @FXML
    private ComboBox<String> combo_session;

    @FXML
    private Button importImage;

    @FXML
    private ImageView imported;

    @FXML
    private TextArea movieDescription;

    @FXML
    private TextField movieDuration;

    @FXML
    private DatePicker movieEndDate;

    @FXML
    private TextField moviePrice;

    @FXML
    private DatePicker movieStartDate;

    @FXML
    private TextField movieTitle;

    @FXML
    private TextField numberTickets;

    @FXML
    private TextField rating;

    @FXML
    private TextField release_year;

    @FXML
    private Label errorDate;

    private File file;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Movie movie = null;

    private static String imagePath;

    @FXML
    public void setImage(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG","*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        file = fileChooser.showOpenDialog(null);
        if (file != null){
            System.out.println(file);
            imported.setImage(new Image("file:"+file));
            System.out.println(file.getAbsolutePath());
        }else{
            System.out.println("null");
        }
        this.imagePath = file.getAbsolutePath();
        if (imagePath.contains("\\")){
            imagePath = imagePath.substring(imagePath.lastIndexOf('\\')+1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] genres = {"Action", "Thriller", "Comedy", "Horror"};
        combo_genre.getItems().addAll(genres);
        String[] languages = {"English", "Russian", "Uzbek"};
        combo_languages.getItems().addAll(languages);
        String[] sessions = {"10:00", "12:00", "15:00", "18:00", "21:00"};
        combo_session.getItems().addAll(sessions);
    }

    public void addAction(ActionEvent event) throws SQLException {
        DataBaseConnect dataCon = new DataBaseConnect();
        if (isNumeric(movieDuration.getText())&&isNumeric(moviePrice.getText())&&isNumeric(numberTickets.getText())){
            LocalDate noww = LocalDate.now();
            if (noww.isBefore(movieStartDate.getValue())){
                if (movieStartDate.getValue().isBefore(movieEndDate.getValue())){
                    String sql = "INSERT INTO `movies`(`title`, `description`, `genre`, `language`, `duration`, `session`, `imdb`, `release_year`, `start_date`, `end_date`, `number_tickets`, `price`, `image_path`) VALUES ('"+movieTitle.getText()+"','"+movieDescription.getText()+"','"+combo_genre.getValue()+"','"+combo_languages.getValue()+"','"+Integer.valueOf(movieDuration.getText())+"','"+combo_session.getValue()+"','"+Float.valueOf(rating.getText())+"','"+Integer.valueOf(release_year.getText())+"','"+movieStartDate.getValue()+"','"+movieEndDate.getValue()+"','"+Integer.valueOf(numberTickets.getText())+"','"+Integer.valueOf(moviePrice.getText())+"','"+imagePath+"')";
                    dataCon.insertData(sql);
                    for (LocalDate date = movieStartDate.getValue(); date.isBefore(movieEndDate.getValue());date = date.plusDays(1)) {
                        String sql2 = "INSERT INTO `tickets`(`title`, `date`, `number_tickets`) VALUES ('"+movieTitle.getText()+"','"+date+"','"+Integer.valueOf(numberTickets.getText())+"')";
                        dataCon.insertData(sql2);
                    }
                }else {
                    errorDate.setText("Wrong end-date!");
                }
            }else {
                errorDate.setText("Start-date should be later than now!");
            }
        }else System.out.println("Something went wrong");
        AdminPanelController adc = new AdminPanelController();
        adc.refreshable();
        Stage stage = (Stage) addMovieBtn.getScene().getWindow();
        stage.close();
    }

    public boolean isNumeric(String val){
        try{
            Integer.valueOf(val);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
