package com.BankSystem.BankSystem.Models.Users;

import jakarta.persistence.*;

@Embeddable
public class Address {

    private String streetName;
    private String city;

    public Address(String streetName, String city) {
        this.streetName = streetName;
        this.city = city;
    }

    public Address() {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
