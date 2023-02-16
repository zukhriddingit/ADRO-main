package com.example.adro;

import com.itextpdf.text.pdf.Barcode39;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Random;

public class GetpdfController {

    @FXML
    private Button downloadPdf;

    Random random = new Random();

    @FXML
    void downloadPdf(ActionEvent event) {
        String[] columnTitles = {"Movie", "Number of Tickets", "Total Price in $"};
        String[][] data = {
                {"Top Gun", "3", "30.00"},
                {"Matrix", "3", "45.00"},
                {"Nope", "1", "15.00"},
                {"Black Panther", "4", "60.00"}
        };

        //barcode
//        String barcode = String.valueOf(random.nextFloat()*10000);
//        Barcode39 barcod = new Barcode39();
//        barcod.setCode(barcode);

//        PdfGenerator pdfGenerator = new PdfGenerator();
        PdfGenerator.generatePdf("Ticket Reservation Receipt", columnTitles, data);
    }
}