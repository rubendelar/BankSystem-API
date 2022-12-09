package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Savings extends AccountType {


    @DecimalMin(value = "100")
    @DecimalMax(value = "1000")
    private BigDecimal minimumBalance;

    @DecimalMax(value = "0.5")
    private BigDecimal interestRate;

    private LocalDate lastTimeInterestRate = LocalDate.now();

    public Savings(AccountHolders primaryOwner, AccountHolders secondaryOwner,
                   BigDecimal balance, String secretKey, BigDecimal minimumBalance,
                   BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance, secretKey);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);

    }

    public Savings() {
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
        if (interestRate == null) this.interestRate = new BigDecimal("0.0025");
        else this.interestRate = interestRate;
    }

    public LocalDate getLastTimeInterestRate() {
        return lastTimeInterestRate;
    }

    public void setLastTimeInterestRate(LocalDate lastTimeInterestRate) {
        this.lastTimeInterestRate = lastTimeInterestRate;
    }
}
