package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class CreditCard extends AccountType {

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit;

    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;

    private LocalDate lastTimeInterestRate = LocalDate.now();

    public CreditCard(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, Status status, BigDecimal creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit == null) this.creditLimit = new BigDecimal(100);
        else this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null) this.interestRate = new BigDecimal(0.2);
        else this.interestRate = interestRate;
    }

    public LocalDate getLastTimeInterestRate() {
        return lastTimeInterestRate;
    }

    public void setLastTimeInterestRate(LocalDate lastTimeInterestRate) {
        this.lastTimeInterestRate = lastTimeInterestRate;
    }
}

