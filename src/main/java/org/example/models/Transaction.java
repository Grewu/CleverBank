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
public class Transaction { private int id;
    @Getter
    private String senderAccountNumber;
    @Getter
    private String receiverAccountNumber;
    @Getter
    private BigDecimal amount;
    @Getter
    private String transactionType;
    @Getter
    private LocalDateTime timestamp;
}
