package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Checking extends AccountType {


    private final BigDecimal minimumBalance = new BigDecimal(250);

    private final BigDecimal monthlyMaintenanceFee = new BigDecimal(12);

    public Checking(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, String secretKey, Status status) {
        super(primaryOwner, secondaryOwner, balance, secretKey, status);
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

}
