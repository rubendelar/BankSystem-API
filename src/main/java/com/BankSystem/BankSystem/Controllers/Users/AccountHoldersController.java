package com.BankSystem.BankSystem.Controllers.Users;

import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//        AccountHolders should be able to access their own account balance
//
//        Account holders should be able to transfer money from any of their accounts to any
//        other account (regardless of owner). The transfer should only be processed if the account
//        has sufficient funds. The user must provide the Primary or Secondary owner’s name and the
//        d of the account that should receive the transfer.

@RestController
public class AccountHoldersController {

    @Autowired
    AccountHoldersRepository accountHoldersRepository;



}
