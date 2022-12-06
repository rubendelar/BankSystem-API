package com.BankSystem.BankSystem.Models.Users;


import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import jakarta.persistence.*;

import java.util.List;

@Inheritance
@Entity
public abstract class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;


}
