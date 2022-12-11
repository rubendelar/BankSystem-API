package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditCard extends AccountType {

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit;

    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate lastTimeInterestRate = LocalDate.now();

    public CreditCard(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance,
                      String secretKey, BigDecimal creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner, balance, secretKey);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);

    }

    public CreditCard() {
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
        if (interestRate == null) this.interestRate = new BigDecimal("0.2");
        else this.interestRate = interestRate;
    }

    public LocalDate getLastTimeInterestRate() {
        return lastTimeInterestRate;
    }

    public void setLastTimeInterestRate(LocalDate lastTimeInterestRate) {
        this.lastTimeInterestRate = lastTimeInterestRate;
    }
}

