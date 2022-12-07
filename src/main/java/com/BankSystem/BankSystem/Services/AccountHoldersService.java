package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Models.Accounts.*;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.CheckingRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.CreditCardRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (accountHoldersRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Account not found");
        else return accountHoldersRepository.findById(id).get().getPrimaryOwnerList();
    }

    public BigDecimal getBalance(Integer id) {

        LocalDate todaysDateMinus1Year = LocalDate.now().minusYears(1);
        LocalDate todaysDateMinus1Month = LocalDate.now().minusMonths(1);
        AccountType account = accountTypeRepository.findById(id).get();

        if (accountTypeRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Account not found");

        if (accountTypeRepository.findById(id).get().getBalance() == null) throw new IllegalArgumentException("Account has not funds");



       if (accountTypeRepository.findById(id).get().getAccountCreation().isBefore(todaysDateMinus1Year)) {
           if (account instanceof Savings) {
               Savings savingsAccount = (Savings) account;
               if (Period.between(savingsAccount.getLastTimeInterestRate(), LocalDate.now()).getYears() >= 1) {
                   savingsAccount.setBalance(savingsAccount.getBalance().add(savingsAccount.getBalance().multiply(savingsAccount.getInterestRate())));
                   savingsAccount.setLastTimeInterestRate(LocalDate.now());
                   savingsRepository.save(savingsAccount);
                   return savingsAccount.getBalance();
               }

           }
       }

       if (accountTypeRepository.findById(id).get().getAccountCreation().isBefore(todaysDateMinus1Month)) {
           if(account instanceof CreditCard){
               CreditCard creditCardAccount = (CreditCard) account;
               if(Period.between(creditCardAccount.getLastTimeInterestRate(), LocalDate.now()).getMonths() >= 1){
                   creditCardAccount.setBalance(creditCardAccount.getBalance().add(creditCardAccount.getBalance().multiply(creditCardAccount.getInterestRate())));
                   creditCardAccount.setLastTimeInterestRate(LocalDate.now());
                   creditCardRepository.save(creditCardAccount);
                   return creditCardAccount.getBalance();
               }

           }

       };

        return accountTypeRepository.findById(id).get().getBalance();
    }



    //To transfer money from any of their accounts to any other account:
    public void transfer(Integer sendingId, Integer receivingId, String receivingOwnersName, BigDecimal transferFunds ) {

        if (accountTypeRepository.findById(sendingId).isEmpty()) throw new IllegalArgumentException("Sending account not found");
        if (accountTypeRepository.findById(sendingId).get().getBalance() == null) throw new IllegalArgumentException("This account does have not funds yet");
        if (accountTypeRepository.findById(sendingId).get().getStatus().equals(Status.FROZEN)) throw new IllegalArgumentException("Sending Account is Frozen");
        BigDecimal sendingAccountBalance = accountTypeRepository.findById(sendingId).get().getBalance();

        if (accountTypeRepository.findById(receivingId).isEmpty()) throw new IllegalArgumentException("Receiving account not found");
        AccountHolders accountTypeReceiving = accountTypeRepository.findById(receivingId).get().getPrimaryOwner();

        if (sendingAccountBalance.compareTo(transferFunds) == -1) throw new IllegalArgumentException("Not enough funds to make this transaction");
        if (accountTypeRepository.findById(receivingId).get().getStatus().equals(Status.FROZEN)) throw new IllegalArgumentException("Receiving Account is Frozen");

        if (accountTypeReceiving.equals(receivingOwnersName)) {
            accountTypeRepository.findById(sendingId).get().setBalance(sendingAccountBalance.subtract(transferFunds));
            accountTypeRepository.findById(receivingId).get().setBalance(getBalance(receivingId).add(transferFunds));
        } else throw new IllegalArgumentException("Owners receiving name does not mach with this account");


    }


}
