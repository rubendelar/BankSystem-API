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

    public UserType() {
    }

    public UserType( String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}