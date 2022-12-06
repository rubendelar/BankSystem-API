package com.BankSystem.BankSystem.Models.Users;

import jakarta.persistence.*;

@Embeddable
public class Address {

    private String streetName;
    private String City;

    public Address(String streetName, String city) {
        this.streetName = streetName;
        City = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
