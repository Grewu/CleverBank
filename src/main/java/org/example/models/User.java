package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String email;
    @Getter
    private BigDecimal cash;

    public User(String username, String password, String email, BigDecimal cash) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.cash = cash;
    }
}
