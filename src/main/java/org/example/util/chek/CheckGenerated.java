package org.example.util.chek;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckGenerated {
    private static  int countOfCheck;

    public static void generateCheck(String operationType, String accountNumber, BigDecimal amount, String nameOfBankSender, String nameOfBankRecieving, String receivingAccountNumber) {
        String fileName = "check_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".txt";

        try {
            File checkFolder = new File("check");
            if (!checkFolder.exists()) {
                checkFolder.mkdir();
            }

            File checkFile = new File(checkFolder, fileName);
            FileWriter writer = new FileWriter(checkFile);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            writer.write("|------------------------------------------|\n");
            writer.write("|               Банковский чек             |\n");
            writer.write("| Чек:                                   " + countOfCheck + " |\n");
            writer.write("| " + LocalDateTime.now().format(dateFormatter) + "                      ");
            writer.write(LocalDateTime.now().format(timeFormatter) + " |\n");
            writer.write("| Тип транзакции:                   " + operationType + "|\n");
            writer.write("| Банк получателя:                   " + nameOfBankRecieving + " |\n");
            writer.write("| Банк отправителя:                  " + nameOfBankSender + " |\n");
            writer.write("| Счет отправителя:                " + accountNumber + "|\n");
            writer.write("| Счет получателя:                " + receivingAccountNumber + "|\n");
            writer.write("| Сумма :                          " + amount + " |\n");
            writer.write("|------------------------------------------|");
            writer.close();
            countOfCheck++;
            System.out.println("Чек успешно сохранен: " + checkFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении чека: " + e.getMessage());
        }
    }

}
