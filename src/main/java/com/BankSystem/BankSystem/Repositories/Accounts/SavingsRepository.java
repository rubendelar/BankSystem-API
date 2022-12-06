package com.BankSystem.BankSystem.Repositories.Accounts;

import com.BankSystem.BankSystem.Models.Accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository <Savings, Integer> {
}
