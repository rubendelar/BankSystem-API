package com.BankSystem.BankSystem.Repositories.Users;

import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AccountHoldersRepository extends JpaRepository <AccountHolders, Integer> {


    Integer findByName(String name);
}
