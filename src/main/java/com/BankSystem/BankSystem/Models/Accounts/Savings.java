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

    @DecimalMin(value = "100")
    @DecimalMax(value = "1000")
    private BigDecimal minimumBalance;

    @DecimalMin(value = "0.5")
    private BigDecimal interestRate;

    public Savings(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, Status status, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance, status);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if (minimumBalance == null) this.minimumBalance = new BigDecimal(1000);
        else this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null) this.interestRate = new BigDecimal(0.0025);
        else this.interestRate = interestRate;
    }
}
