package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.*;
import com.BankSystem.BankSystem.Models.DTO.CheckingStudentCheckingAccountCreationDTO;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.*;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
import com.BankSystem.BankSystem.Repositories.Users.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


@Service
public class AdminsService {


    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    UserTypeRepository userTypeRepository;
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

    public CreditCard createCreditCardAccount(CreditCard creditCardAccount){ return  creditCardRepository.save(creditCardAccount);}

    public Savings createSavingsAccount(Savings savingsAccount){
        return savingsRepository.save(savingsAccount);
    }


    public AccountType createCheckingAccount( CheckingStudentCheckingAccountCreationDTO checkingStudentCheckingAccountCreationDTO){


        AccountHolders primaryOwner = accountHoldersRepository.getReferenceById(checkingStudentCheckingAccountCreationDTO.getPrimaryOwnerUserId());
        AccountHolders secondaryOwner;
        if (checkingStudentCheckingAccountCreationDTO.getSecondaryOwnerUserId() != null) {
             secondaryOwner = accountHoldersRepository.getReferenceById(checkingStudentCheckingAccountCreationDTO.getSecondaryOwnerUserId());
        } else secondaryOwner = null;


        if (Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() >= 24){
            Checking checkingAccount = new Checking(primaryOwner,
                    secondaryOwner, checkingStudentCheckingAccountCreationDTO.getBalance(),
                    checkingStudentCheckingAccountCreationDTO.getSecretKey());
            return accountTypeRepository.save(checkingAccount);

        } else {
            StudentChecking studentCheckingAccount = new StudentChecking(primaryOwner,
                    secondaryOwner, checkingStudentCheckingAccountCreationDTO.getBalance(),
                    checkingStudentCheckingAccountCreationDTO.getSecretKey());
           return accountTypeRepository.save(studentCheckingAccount);

        }
    }

    public StudentChecking createStudentCheckingAccount(StudentChecking studentCheckingAccount){ return studentCheckingRepository.save(studentCheckingAccount);}


    public ThirdParty createThirdPartyUser (String name) {
        ThirdParty thirdParty = new ThirdParty(name);
        return thirdPartyRepository.save(thirdParty);
    }

    public void deleteAccount (Integer id) {
        if (accountTypeRepository.findById(id).isPresent()){
            accountTypeRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Account with provided ID");

    }

    public void deleteThirdPartyUser (Integer id) {
        if (thirdPartyRepository.findById(id).isPresent()){
        thirdPartyRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Third Party user with provided ID");

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
