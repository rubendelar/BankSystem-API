package com.BankSystem.BankSystem.Models.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ThirdParty extends AccountHolders {

    private String key;
}
