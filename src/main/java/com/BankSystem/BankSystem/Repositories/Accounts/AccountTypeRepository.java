package com.BankSystem.BankSystem.Repositories.Accounts;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository <AccountType, Integer> {


}
