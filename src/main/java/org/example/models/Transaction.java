package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private int id;
    @Getter
    private String senderAccountNumber;
    @Getter
    private String receiverAccountNumber;
    @Getter
    private String time;
    @Getter
    private BigDecimal amount;
    @Getter
    private String transactionType;
    @Getter
    private LocalDateTime timestamp;

    public Transaction(String time, String type, BigDecimal amount) {
        this.time = time;
        this.transactionType = type;
        this.amount = amount;
    }

    public Transaction(int accountId, String sender, String receiver, BigDecimal interestAmount, String transfer, LocalDateTime time) {
        this.id = accountId;
        this.senderAccountNumber = sender;
        this.receiverAccountNumber = receiver;
        this.amount = interestAmount;
        this.transactionType = transfer;
        this.timestamp = time;
    }
}
