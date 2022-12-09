package com.BankSystem.BankSystem.Controllers;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Accounts.CreditCard;
import com.BankSystem.BankSystem.Models.Accounts.Savings;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Services.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AdminsController {

    @Autowired
    AdminsService adminService;

    @Autowired
    SavingsRepository savingsRepository;


    @GetMapping("/account-balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getAnyAccountBalance(@RequestParam Integer id) {
        return adminService.getAnyAccountBalance(id);
    }

    @PatchMapping("/account-setBalance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private AccountType setAnyAccountBalance(@RequestParam Integer id, BigDecimal fund) {
        return adminService.setAnyAccountBalance(id, fund);
    }


    @PostMapping("/thirdParty-creation")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@RequestParam String name) {
       return adminService.createThirdPartyUser(name);
    }

    @PostMapping("/savingsAccount-creation")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount(@RequestBody Savings savingsAccount) {
        return adminService.createSavingsAccount(savingsAccount);
    }

    @PostMapping("/creditCardAccount-creation")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody CreditCard creditCardAccount) {
        return adminService.createCreditCardAccount(creditCardAccount);
    }



    @DeleteMapping("/thirdParty-deletion")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteThirdPartyUser(@RequestParam Integer id) {
        return adminService.deleteThirdPartyUser(id);
    }

    @DeleteMapping("/account-deletion")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteAccount(@RequestParam Integer id) {
        return adminService.deleteAccount(id);
    }




    @PatchMapping("/thirdParty-setter")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ThirdParty setThirdPartyUserName(@RequestParam Integer id, String newName) {
        return adminService.setThirdPartyUserName(id,newName);
    }


}
