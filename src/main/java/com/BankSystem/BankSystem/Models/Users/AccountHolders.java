package com.BankSystem.BankSystem.Models.Users;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolders extends UserType {

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @Embedded
    private Address privateAddress;


    private String mailingAddress;

    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    private List<AccountType> primaryOwnerList = new ArrayList<>();

    @OneToMany(mappedBy = "secondaryOwner", cascade = CascadeType.ALL)
    private List<AccountType> secondaryOwnerList = new ArrayList<>();

    public AccountHolders(String name, String password, LocalDate dateOfBirth, Address privateAddress, String mailingAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.privateAddress = privateAddress;
        this.mailingAddress = mailingAddress;

    }

    public AccountHolders() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrivateAddress() {
        return privateAddress;
    }

    public void setPrivateAddress(Address privateAddress) {
        this.privateAddress = privateAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<AccountType> getPrimaryOwnerList() {
        return primaryOwnerList;
    }

    public void setPrimaryOwnerList(List<AccountType> primaryOwnerList) {
        this.primaryOwnerList = primaryOwnerList;
    }

    public List<AccountType> getSecondaryOwnerList() {
        return secondaryOwnerList;
    }

    public void setSecondaryOwnerList(List<AccountType> secondaryOwnerList) {
        this.secondaryOwnerList = secondaryOwnerList;
    }
}
