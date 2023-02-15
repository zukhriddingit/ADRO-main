package com.example.adro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;



public class AsilbeksVersionMyPageController implements Initializable{
    @FXML
    private Label releaseDate;
    @FXML
    private Label imdb;

    @FXML
    private Label language;


    @FXML
    private ImageView MoviePageImg;

    @FXML
    private ImageView star;

    private static String movie_path;
    private static String label;
    private static String description;
    private static String genre;

    public static String getLang() {
        return lang;
    }

    public static void setLang(String lang) {
        AsilbeksVersionMyPageController.lang = lang;
    }

    public static float getiMDB() {
        return iMDB;
    }

    public static void setiMDB(float iMDB) {
        AsilbeksVersionMyPageController.iMDB = iMDB;
    }

    private static String lang;

    private static int relese;
    private static float iMDB;

    public static int getRelese() {
        return relese;
    }

    public static void setRelese(int relese) {
        AsilbeksVersionMyPageController.relese = relese;
    }

    @FXML
    private Label MovieName;

    @FXML
    private Label Genre;

    @FXML
    private Label MovieDescription;

    public String getMovie_path() {
        return movie_path;
    }

    public void setMovie_path(String movie_path) {
        this.movie_path = movie_path;
    }

    public AsilbeksVersionMyPageController(String movie_path, String label, String description, String genre) {
        this.movie_path = movie_path;
        this.label = label;
        this.description = description;
        this.genre = genre;
    }

    public AsilbeksVersionMyPageController(){}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("src/main/java/pictures");
        try {
            Image image = new Image(file.toURI().toURL().toString()+"sta.jpg");
            Image image1 = new Image(file.toURI().toURL().toString()+movie_path);
            System.out.println(movie_path);
            MoviePageImg.setImage(image1);
            star.setImage(image);
            MovieName.setText(label);
            Genre.setText(genre);
            MovieDescription.setText(description);
            releaseDate.setText(Integer.toString(relese));
            language.setText(lang);
            imdb.setText(Float.toString(iMDB));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }

    public void addToCart(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CartPage.fxml")),1366,700);
        dialogStage.setScene(scene);
        dialogStage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader(SignUpPageApplication.class.getResource("Dashboard.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
//        dc.searchAction(scene);
    }
}