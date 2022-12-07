package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountHoldersService {

    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;


    public List<AccountType> getAccounts(Integer id) {
        AccountHolders accountHolders = accountHoldersRepository.findById(id).get();
        return accountHolders.getPrimaryOwnerList();
    }
    public BigDecimal getBalance(Integer id) {
        return accountTypeRepository.findById(id).get().getBalance();
    }

    //encontrar cuenta 1
//    encontrar cuenta 2
//    comprovar que el nombre coincide con la cuenta
//    comprovaciones que las cuentas tienen el status correcto i dinero para enviar
}
