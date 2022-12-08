package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Accounts.Status;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartyService {

    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public AccountType thirdPartyTransfer (String hashKey, Integer thirdPartyId, Integer AccountId, String accountSecretKey, BigDecimal transferFunds) {
        if (thirdPartyRepository.findById(thirdPartyId).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty not found");
        if (!hashKey.equals(thirdPartyRepository.findById(thirdPartyId).get().getHashKey())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty not found");
        if (accountTypeRepository.findById(AccountId).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        if (accountTypeRepository.findById(AccountId).get().getStatus().equals(Status.FROZEN)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sending Account is Frozen");
        if (!accountSecretKey.equals(accountTypeRepository.findById(AccountId).get().getSecretKey())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Secret Key");

        AccountType account = accountTypeRepository.findById(AccountId).get();
        account.setBalance(account.getBalance().add(transferFunds));
        return accountTypeRepository.save(account);
    }

}
