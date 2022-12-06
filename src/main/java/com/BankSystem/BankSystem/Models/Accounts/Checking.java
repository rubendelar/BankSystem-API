package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Checking extends AccountType {

    private String secretKey;

    private BigDecimal minimumBalance;

    private BigDecimal monthlyMaintenanceFee;

}
