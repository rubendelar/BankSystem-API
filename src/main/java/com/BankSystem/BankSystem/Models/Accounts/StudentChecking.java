package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class StudentChecking extends AccountType {

    private String secretKey;

    public StudentChecking(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, Status status, String secretKey) {
        super(primaryOwner, secondaryOwner, balance, status);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}

