package com.BankSystem.BankSystem.Models.Users;

import jakarta.persistence.Entity;


@Entity
public class Admins extends UserType {

    public Admins(String name, String password) {
        super(name, password);
    }
    public Admins() {
    }
}
