package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class CreditCard extends AccountType {

    private BigDecimal creditLimit;

    private BigDecimal interestRate;


}

