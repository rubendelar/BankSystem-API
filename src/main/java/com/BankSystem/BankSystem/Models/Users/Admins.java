package com.BankSystem.BankSystem.Models.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admins extends UserType {

    public Admins(String name, String password) {
        super(name, password);
    }

}
