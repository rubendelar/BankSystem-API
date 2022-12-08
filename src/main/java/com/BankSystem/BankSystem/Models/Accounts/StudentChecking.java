package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class StudentChecking extends AccountType {

    public StudentChecking(AccountHolders primaryOwner, AccountHolders secondaryOwner,
                           BigDecimal balance, String secretKey, Status status) {
        super(primaryOwner, secondaryOwner, balance, secretKey, status);
    }
}

