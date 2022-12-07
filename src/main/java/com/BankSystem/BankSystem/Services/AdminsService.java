package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Users.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminsService {


    @Autowired
    AccountTypeRepository accountTypeRepository;

    public BigDecimal getAnyAccountBalance(Integer id) {
        if (accountTypeRepository.findById(id).isEmpty() || accountTypeRepository.findById(id).get().getBalance() == null) throw new IllegalArgumentException("Account not found");
        else return accountTypeRepository.findById(id).get().getBalance();
    }

    public void setAnyAccountBalance(Integer id, BigDecimal fund) {
        if (accountTypeRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Account not found");
        else accountTypeRepository.findById(id).get().setBalance(fund);
    }
}
