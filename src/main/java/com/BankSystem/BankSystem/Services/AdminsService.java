package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Users.AdminsRepository;
import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminsService {


    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public BigDecimal getAnyAccountBalance(Integer id) {
        if (accountTypeRepository.findById(id).isEmpty() || accountTypeRepository.findById(id).get().getBalance() == null) throw new IllegalArgumentException("Account not found");
        else return accountTypeRepository.findById(id).get().getBalance();
    }

    public void setAnyAccountBalance(Integer id, BigDecimal fund) {
        if (accountTypeRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Account not found");
        else accountTypeRepository.findById(id).get().setBalance(fund);
    }

    //El hashKey esta configurado como autogenerado pero tengo que ponerlo igual ?
    public void createThirdPartyUser (String hashKey, String name) {
        ThirdParty thirdParty = new ThirdParty(hashKey, name);
        thirdPartyRepository.save(thirdParty);
    }

    public void setThirdPartyUserName (Integer id, String newName) {
       if (thirdPartyRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Can not find Third Party user with provided ID");
       else {
           thirdPartyRepository.findById(id).get().setName(newName);

           //Como guardar esto el nuevo nombre en el repositorio? Deberia haberle puesto un nombre para hacer el .save()?
       }
    }
}
