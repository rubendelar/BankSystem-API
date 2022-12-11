package com.BankSystem.BankSystem.Models.DTO;


import java.math.BigDecimal;

public class CheckingStudentCheckingAccountCreationDTO {



    private Integer primaryOwnerUserId;

    private Integer secondaryOwnerUserId;

    private BigDecimal balance;

    private String secretKey;

    public CheckingStudentCheckingAccountCreationDTO(Integer primaryOwnerUserId, Integer secondaryOwnerUserId, BigDecimal balance, String secretKey) {
        this.primaryOwnerUserId = primaryOwnerUserId;
        this.secondaryOwnerUserId = secondaryOwnerUserId;
        this.balance = balance;
        this.secretKey = secretKey;
    }

    public Integer getPrimaryOwnerUserId() {
        return primaryOwnerUserId;
    }

    public void setPrimaryOwnerUserId(Integer primaryOwnerUserId) {
        this.primaryOwnerUserId = primaryOwnerUserId;
    }

    public Integer getSecondaryOwnerUserId() {
        return secondaryOwnerUserId;
    }

    public void setSecondaryOwnerUserId(Integer secondaryOwnerUserId) {
        this.secondaryOwnerUserId = secondaryOwnerUserId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
