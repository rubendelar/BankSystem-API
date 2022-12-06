package com.BankSystem.BankSystem.Repositories.Accounts;

import com.BankSystem.BankSystem.Models.Accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository <CreditCard, Integer> {
}
