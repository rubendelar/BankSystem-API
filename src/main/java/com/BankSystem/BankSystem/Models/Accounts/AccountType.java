package com.BankSystem.BankSystem.Models.Accounts;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    private AccountHolders primaryOwner;

    @ManyToOne
    private AccountHolders secondaryOwner;

    private BigDecimal balance;

    private BigDecimal penaltyFee = BigDecimal.valueOf(40);

    private String secretKey;

    private LocalDate accountCreation = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public AccountType() {
    }

    public AccountType(AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal balance, String secretKey) {
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
        this.secretKey = secretKey;
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
        if (this instanceof Savings savingsAccount) {
            if (savingsAccount.getBalance().compareTo(savingsAccount.getMinimumBalance())==-1){
                savingsAccount.setBalance(getBalance().subtract(getPenaltyFee()));
            }
        }
        if (this instanceof Checking checkingAccount) {
            if (checkingAccount.getBalance().compareTo(checkingAccount.getMinimumBalance())==-1){
                checkingAccount.setBalance(getBalance().subtract(getPenaltyFee()));
            }
        }
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
