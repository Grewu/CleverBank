package org.example.util.pdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.example.models.Transaction;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class StatementPDFGenerator {
    public static void generateStatementPDF(String bankName, String clientName, String accountNumber,
                                            String openingDate, String balance, List<Transaction> transactionList) {

        String outputPDFPath = Paths.get("C:\\Users\\user\\IdeaProjects\\CleverBank\\check",
                "statement_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSSSS");
        LocalTime time = LocalTime.parse("15:04:19.978594500", formatter);

        try (var pdfDocument = new PdfDocument(new PdfWriter(outputPDFPath))) {
            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont("D:\\times.ttf", "Identity-H", true);
            String content =
                    "                                               \n" +
                            "                    \0           " + "               \t \t        Statment       " + "\r" + " " +
                            " " +
                            "                   \0                " + "                \t\t     " + bankName + "                      \r" +
                            "Client                                                                                         | " + clientName + "\n" +
                            "Account                                                                                     | " + accountNumber + "\n" +
                            "Currency                                                                                    | BYN\n" +
                            "Opening Date                                                                             | " + time + "\n" +
                            "Period                                                                                         | " + openingDate + " - " +
                            LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\n" +
                            "Formation Date & Time                                                             | " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                            "Balance                                                                                       | " + balance + "\n" +
                            "Date                                     |   Note                                            | Amount\n" +
                            "----------------------------------------------------------------------------------------------";
            for (Transaction transaction : transactionList) {
                content += "\n" + transaction.getTime() + "                           | " + transaction.getTransactionType() + "                                       |   " + transaction.getAmount() +"  BYN";
            }

            Paragraph paragraph = new Paragraph(content).setFont(font).setFontSize(12);
            document.add(paragraph);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
