package org.example.util.pdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.example.models.Transaction;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatementPDFGenerator {
    private static final String PDF_OUTPUT_DIR = "check" + FileSystems.getDefault().getSeparator() + "pdf";
    private static final String TXT_OUTPUT_DIR = "check" + FileSystems.getDefault().getSeparator() + "txt";
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private static final String TIME_FORMAT = "HH:mm:ss.SSSSSSSSS";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String DATE_TIME_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

    public static void generateStatementPDF(String bankName, String clientName, String accountNumber,
                                            String openingDate, String balance, List<Transaction> transactionList) {
        String outputPDFPath = Paths.get(PDF_OUTPUT_DIR, "statement_" + getCurrentDateTimeFormatted(DATE_TIME_FORMAT) + ".pdf").toString();

        try (var pdfDocument = new PdfDocument(new PdfWriter(outputPDFPath))) {
            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont("D:\\times.ttf", "Identity-H", true);
            String content = generatePDFContent(bankName, clientName, accountNumber, openingDate, balance, transactionList);

            Paragraph paragraph = new Paragraph(content).setFont(font).setFontSize(12);
            document.add(paragraph);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateStatementTXT(String bankName, String clientName, String accountNumber,
                                            String openingDate, String balance, List<Transaction> transactionList) {
        String outputTXTPath = TXT_OUTPUT_DIR +
                FileSystems.getDefault().getSeparator() +
                "statement_" + getCurrentDateTimeFormatted(DATE_TIME_FORMAT) + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputTXTPath))) {
            String content = generateTXTContent(bankName, clientName, accountNumber, openingDate, balance, transactionList);

            writer.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generatePDFContent(String bankName, String clientName, String accountNumber,
                                             String openingDate, String balance, List<Transaction> transactionList) {
        return "                                               \n" +
                "                    \0           " + "               \t \t        Statement       " + "\r" + " " +
                " " +
                "                   \0                " + "                \t\t     " + bankName + "                      \r" +
                "Client                                                                                         | " + clientName + "\n" +
                "Account                                                                                     | " + accountNumber + "\n" +
                "Currency                                                                                    | BYN\n" +
                "Opening Date                                                                             | " + getCurrentTimeFormatted() + "\n" +
                "Period                                                                                         | " + openingDate + " - " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "\n" +
                "Formation Date & Time                                                             | " + getCurrentDateTimeFullFormatted() + "\n" +
                "Balance                                                                                       | " + balance + "\n" +
                "Date                                     |   Note                                            | Amount\n" +
                "----------------------------------------------------------------------------------------------" +
                generateTransactionLines(transactionList);
    }

    private static String generateTransactionLines(List<Transaction> transactionList) {
        StringBuilder content = new StringBuilder();
        for (Transaction transaction : transactionList) {
            content.append("\n").append(transaction.getTime()).append("                           | ").append(transaction.getTransactionType()).append("                          |   ").append(transaction.getAmount()).append("  BYN");
        }
        return content.toString();
    }

    private static String generateTXTContent(String bankName, String clientName, String accountNumber,
                                             String openingDate, String balance, List<Transaction> transactionList) {
        return "                                                \n" +
                "                     " + "               \t \t  Statement       " + "\n" +
                "                                             " + bankName + "  \n" +
                "Client                                                               | " + clientName + "\n" +
                "Account                                                              | " + accountNumber + "\n" +
                "Currency                                                             | BYN\n" +
                "Opening Date                                                         | " + getCurrentTimeFormatted() + "\n" +
                "Period                                                               | " + openingDate + " - " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "\n" +
                "Formation Date & Time                                                | " + getCurrentDateTimeFullFormatted() + "\n" +
                "Balance                                                              | " + balance + "\n" +
                "Date                                 |   Note                        | Amount\n" +
                "----------------------------------------------------------------------------------------------" +
                generateTransactionLines(transactionList);
    }

    private static String getCurrentDateTimeFormatted(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    private static String getCurrentTimeFormatted() {
        return getCurrentDateTimeFormatted(TIME_FORMAT);
    }

    private static String getCurrentDateTimeFullFormatted() {
        return getCurrentDateTimeFormatted(DATE_TIME_FORMAT_FULL);
    }
}
