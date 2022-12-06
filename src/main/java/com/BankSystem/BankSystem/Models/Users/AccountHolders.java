package com.BankSystem.BankSystem.Models.Users;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolders {


    private LocalDate dateOfBirth;

    @Embedded
    private Address privateAddress;

    //opcional
//    @Embedded
//    private String mailingAddress;

    @OneToMany(mappedBy = "AccountHolders", cascade = CascadeType.ALL)
    private List<AccountType> primaryOwnerList;

    @OneToMany(mappedBy = "AccountHolders", cascade = CascadeType.ALL)
    private List<AccountType> secondaryOwnerList;


}
