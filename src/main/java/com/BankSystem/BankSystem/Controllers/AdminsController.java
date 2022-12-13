package com.BankSystem.BankSystem.Controllers;

import com.BankSystem.BankSystem.Models.Accounts.*;
import com.BankSystem.BankSystem.Models.DTO.CheckingStudentCheckingAccountCreationDTO;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Services.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class AdminsController {

    @Autowired
    AdminsService adminService;


    @GetMapping("/account-balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getAnyAccountBalance(@RequestParam Integer id) {
        return adminService.getAnyAccountBalance(id);
    }

    @PatchMapping("/account-setBalance")
    @ResponseStatus(HttpStatus.CREATED)
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
        System.err.println(savingsAccount);
        return adminService.createSavingsAccount(savingsAccount);
    }

    @PostMapping("/creditCardAccount-creation")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody CreditCard creditCardAccount) {
        return adminService.createCreditCardAccount(creditCardAccount);
    }

    @PostMapping("/checkingAccount-creation")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountType createCheckingAccount(@RequestBody CheckingStudentCheckingAccountCreationDTO checkingStudentCheckingAccountCreationDTO) {
        return adminService.createCheckingAccount(checkingStudentCheckingAccountCreationDTO);
    }

    @PostMapping("/directStudentCheckingAccount-creation")
    @ResponseStatus(HttpStatus.CREATED)
    StudentChecking createStudentCheckingAccount(@RequestBody StudentChecking studentCheckingAccount) {
        return adminService.createStudentCheckingAccount(studentCheckingAccount);
    }


    @DeleteMapping("/thirdParty-deletion")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteThirdPartyUser(@RequestParam Integer id) {
        adminService.deleteThirdPartyUser(id);
    }

    @DeleteMapping("/account-deletion")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@RequestParam Integer id) {
         adminService.deleteAccount(id);
    }


    @PatchMapping("/thirdParty-setter")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ThirdParty setThirdPartyUserName(@RequestParam Integer id, String newName) {
        return adminService.setThirdPartyUserName(id,newName);
    }


}
