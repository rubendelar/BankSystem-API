package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Users.AdminsRepository;
import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminsService {


    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public BigDecimal getAnyAccountBalance(Integer id) {
        if (accountTypeRepository.findById(id).isEmpty() || accountTypeRepository.findById(id).get().getBalance() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        else return accountTypeRepository.findById(id).get().getBalance();
    }

    public AccountType setAnyAccountBalance(Integer id, BigDecimal fund) {
        if (accountTypeRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        else {
            AccountType accountType = accountTypeRepository.findById(id).get();
            accountType.setBalance(fund);
            return accountTypeRepository.save(accountType);
        }
    }

    public ThirdParty createThirdPartyUser (String name) {
        ThirdParty thirdParty = new ThirdParty(name);
        return thirdPartyRepository.save(thirdParty);
    }

    public String deleteAccount (Integer id) {
        if (accountTypeRepository.findById(id).isPresent()){
            accountTypeRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Account with provided ID");

        return "Account has been deleted";
    }

    public String deleteThirdPartyUser (Integer id) {
        if (thirdPartyRepository.findById(id).isPresent()){
        thirdPartyRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Third Party user with provided ID");

        return "ThirdPartyUser has been deleted";
    }


    public ThirdParty setThirdPartyUserName (Integer id, String newName) {
       if (thirdPartyRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Third Party user with provided ID");
       else {
           ThirdParty thirdParty = thirdPartyRepository.findById(id).get();
           thirdParty.setName(newName);
           return thirdPartyRepository.save(thirdParty);
       }
    }
}
