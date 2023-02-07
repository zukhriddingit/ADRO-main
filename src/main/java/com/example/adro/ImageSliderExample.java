package com.example.adro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageSliderExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create an array of image names
        String[] imageNames = {"image1.jpg", "image2.jpg", "image3.jpg"};

        // Create an ImageView for each image
        ImageView[] imageViews = new ImageView[imageNames.length];
        for (int i = 0; i < imageNames.length; i++) {
            imageViews[i] = new ImageView(new Image(getClass().getResourceAsStream(imageNames[i])));
        }

        // Create a slider
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(imageViews.length - 1);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);

        // Bind the slider value to the index of the image to be displayed
//        imageViews[0].imageProperty().bind(slider.valueProperty().subtract(0).divide(1).multiply(imageViews.length).intValue().add(0).map(index -> imageViews[index].getImage()));

        // Add the slider and ImageView to a layout container
        VBox root = new VBox();
        root.getChildren().addAll(slider, imageViews[0]);

        // Show the scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
