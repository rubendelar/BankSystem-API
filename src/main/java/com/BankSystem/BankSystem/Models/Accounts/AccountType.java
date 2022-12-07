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

    private BigDecimal penaltyFee = BigDecimal.valueOf(40);

    private LocalDate accountCreation = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public AccountType() {
    }

    public AccountType( AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, Status status) {

        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountHolders getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolders getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolders secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getAccountCreation() {
        return accountCreation;
    }

    public void setAccountCreation(LocalDate accountCreation) {
        this.accountCreation = accountCreation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
