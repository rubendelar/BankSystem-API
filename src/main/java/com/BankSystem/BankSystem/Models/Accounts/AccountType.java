package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Inheritance
@Entity
public abstract class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private AccountHolders primaryOwner;

    @ManyToOne
    //HACERLO OPCIONAL
    private AccountHolders secondaryOwner;

    private BigDecimal balance;

    private BigDecimal penaltyFee;

    private LocalDate accountCreation = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;


}
