package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Savings extends AccountType {


    private String secretKey;

    @Min(value = 100)
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);

    @DecimalMin(value = "0.5")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);

}
