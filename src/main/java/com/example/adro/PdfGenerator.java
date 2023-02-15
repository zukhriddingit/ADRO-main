package com.example.adro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PdfGenerator {

    private static final Font HEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font TABLE_HEADING_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font TABLE_CELL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

    public static File generatePdf(String title, String[] columnTitles, String[][] data) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();

            Random rand = new Random();
            long randomNumber = (long)(rand.nextDouble() * 900000000000L) + 100000000000L;
            System.out.println(randomNumber);
            Paragraph paymentID = new Paragraph("PaymentID: " + randomNumber);
            paymentID.setAlignment(Element.ALIGN_LEFT);
            document.add(paymentID);

            Paragraph dateTimeParagraph = new Paragraph(new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(new Date()), TABLE_CELL_FONT);
            dateTimeParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(dateTimeParagraph);

            Paragraph titleParagraph = new Paragraph("Congratulations!", HEADING_FONT);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            titleParagraph.setSpacingAfter(20);
            document.add(titleParagraph);

            PdfPTable table = new PdfPTable(columnTitles.length);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);

            for (String columnTitle : columnTitles) {
                PdfPCell cell = new PdfPCell(new Phrase(columnTitle, TABLE_HEADING_FONT));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            for (String[] row : data) {
                for (String cellData : row) {
                    PdfPCell cell = new PdfPCell(new Phrase(cellData, TABLE_CELL_FONT));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
            }

            document.add(table);

            Paragraph totalParagraph = new Paragraph("Total: $" + calculateTotal(data), TABLE_CELL_FONT);
            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
            totalParagraph.setSpacingBefore(20);
            document.add(totalParagraph);


            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static double calculateTotal(String[][] data) {
        double total = 0;
        for (String[] row : data) {
            total += Double.parseDouble(row[2]);
        }
        return total;
    }
}