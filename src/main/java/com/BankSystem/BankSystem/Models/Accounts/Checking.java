package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity

public class Checking extends AccountType {


    private final BigDecimal minimumBalance = new BigDecimal(250);

    private final BigDecimal monthlyMaintenanceFee = new BigDecimal(12);

    public Checking(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance, secretKey);
    }

    public Checking() {
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

}
