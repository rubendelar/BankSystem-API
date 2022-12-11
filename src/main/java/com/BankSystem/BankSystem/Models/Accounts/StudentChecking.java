package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
public class StudentChecking extends AccountType {

    public StudentChecking(AccountHolders primaryOwner, AccountHolders secondaryOwner,
                           BigDecimal balance, String secretKey) {
        super(primaryOwner, secondaryOwner, balance, secretKey);
    }

    public StudentChecking() {
    }
}

