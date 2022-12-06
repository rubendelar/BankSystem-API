package com.BankSystem.BankSystem.Repositories.Accounts;

import com.BankSystem.BankSystem.Models.Accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository <Checking, Integer> {
}
