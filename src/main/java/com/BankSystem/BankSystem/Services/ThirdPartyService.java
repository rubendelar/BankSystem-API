package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Accounts.Status;
import com.BankSystem.BankSystem.Models.DTO.ThirdPartyTransferDTO;
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

    public AccountType thirdPartyTransfer (ThirdPartyTransferDTO thirdPartyTransferDTO) {
        if (thirdPartyRepository.findById(thirdPartyTransferDTO.getThirdPartyId()).isEmpty()) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty not found");
        if (!thirdPartyTransferDTO.getHashKey().equals(thirdPartyRepository.findById(thirdPartyTransferDTO.getThirdPartyId()).get().getHashKey())) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty not found");
        if (accountTypeRepository.findById(thirdPartyTransferDTO.getAccountId()).isEmpty()) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        if (accountTypeRepository.findById(thirdPartyTransferDTO.getAccountId()).get().getStatus().equals(Status.FROZEN)) throw
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sending Account is Frozen");
        if (!thirdPartyTransferDTO.getAccountSecretKey().equals(accountTypeRepository.findById(thirdPartyTransferDTO.getAccountId()).get().getSecretKey())) throw
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Secret Key");

        AccountType account = accountTypeRepository.findById(thirdPartyTransferDTO.getAccountId()).get();
        account.setBalance(account.getBalance().add(thirdPartyTransferDTO.getTransferFunds()));
        return accountTypeRepository.save(account);
    }

}
