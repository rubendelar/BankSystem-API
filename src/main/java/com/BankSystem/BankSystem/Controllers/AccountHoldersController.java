package com.BankSystem.BankSystem.Controllers;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.DTO.TransferDTO;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.BankSystem.BankSystem.Services.AccountHoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;



@RestController
public class AccountHoldersController {

    @Autowired
    AccountHoldersService accountHoldersService;

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountType> getAccounts(@RequestParam Integer id) {
        return accountHoldersService.getAccounts(id);
    }

    @GetMapping("/accounts-balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@RequestParam Integer id) {
        return accountHoldersService.getBalance(id);
    }

    @PatchMapping("/accounts-transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal transfer(@RequestParam TransferDTO transferDTO) {
        return accountHoldersService.transfer(transferDTO);
    }

}
