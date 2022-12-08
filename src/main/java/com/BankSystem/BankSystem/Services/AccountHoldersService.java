package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.*;
import com.BankSystem.BankSystem.Models.DTO.TransferDTO;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.CheckingRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.CreditCardRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AccountHoldersService {

    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;


    public List<AccountType> getAccounts(Integer id) {

        if (accountHoldersRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        else return accountHoldersRepository.findById(id).get().getPrimaryOwnerList();
    }

    public BigDecimal getBalance(Integer id) {

        //Manual way to compare lapsing Data
        LocalDate todaysDateMinus1Year = LocalDate.now().minusYears(1);
        LocalDate todaysDateMinus1Month = LocalDate.now().minusMonths(1);
        AccountType account = accountTypeRepository.findById(id).get();

        if (accountTypeRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        if (accountTypeRepository.findById(id).get().getBalance() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account has not funds");

        //Adding Interest Rate to the SavingsBalance -yearly
       if (accountTypeRepository.findById(id).get().getAccountCreation().isBefore(todaysDateMinus1Year)) {
           if (account instanceof Savings savingsAccount) {
               if (Period.between(savingsAccount.getLastTimeInterestRate(), LocalDate.now()).getYears() >= 1) {
                   savingsAccount.setBalance(savingsAccount.getBalance().add(savingsAccount.getBalance().multiply(savingsAccount.getInterestRate())));
                   savingsAccount.setLastTimeInterestRate(LocalDate.now());
                   savingsRepository.save(savingsAccount);
                   return savingsAccount.getBalance();
               }

           }
       }

        //Adding Interest Rate to the CreditCardBalance -monthly
       if (accountTypeRepository.findById(id).get().getAccountCreation().isBefore(todaysDateMinus1Month)) {
           if(account instanceof CreditCard creditCardAccount){
               if(Period.between(creditCardAccount.getLastTimeInterestRate(), LocalDate.now()).getMonths() >= 1){
                   creditCardAccount.setBalance(creditCardAccount.getBalance().add(creditCardAccount.getBalance().multiply(creditCardAccount.getInterestRate())));
                   creditCardAccount.setLastTimeInterestRate(LocalDate.now());
                   creditCardRepository.save(creditCardAccount);
                   return creditCardAccount.getBalance();
               }

           }

       };
        //Id it's an account without Interest Rate, this expression will be executed
        return accountTypeRepository.findById(id).get().getBalance();
    }


    //To transfer money from any of their accounts to any other account:
    public BigDecimal transfer(TransferDTO transferDTO) {

        if (accountTypeRepository.findById(transferDTO.getSendingId()).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sending account not found");
        if (accountTypeRepository.findById(transferDTO.getSendingId()).get().getBalance() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This account does have not funds yet");
        if (accountTypeRepository.findById(transferDTO.getSendingId()).get().getStatus().equals(Status.FROZEN)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sending Account is Frozen");
        if (accountTypeRepository.findById(transferDTO.getRecevinginId()).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiving account not found");
        if (accountTypeRepository.findById(transferDTO.getRecevinginId()).get().getStatus().equals(Status.FROZEN)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiving Account is Frozen");

        AccountType sendingAccount = accountTypeRepository.findById(transferDTO.getSendingId()).get();
        AccountType receivingAccount = accountTypeRepository.findById(transferDTO.getRecevinginId()).get();

        if(sendingAccount.getBalance().compareTo(transferDTO.getTransferFunds()) > 0) throw
         new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account doesn't have enough funds");

        if(receivingAccount.getPrimaryOwner().getName().equals(transferDTO.getReceivingOwnersName())
                || receivingAccount.getSecondaryOwner().getName().equals(transferDTO.getReceivingOwnersName())){

            sendingAccount.setBalance(sendingAccount.getBalance().subtract(transferDTO.getTransferFunds()));
            receivingAccount.setBalance(receivingAccount.getBalance().add(transferDTO.getTransferFunds()));
            accountTypeRepository.saveAll(List.of(receivingAccount, sendingAccount));
        }

        return sendingAccount.getBalance();
    }

}
