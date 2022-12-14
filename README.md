# BankSystem-API

This is a Bank API, built with Java and Spring Boot with MySQL

## Functionalities

The system has 4 types of accounts: StudentChecking, Checking, Savings, and CreditCard.
There is an abstract class called AccountType.

The system has 3 types of Users: Admins, AccountHolders and ThirdParty.
There is an abstract class called UsersType.

There are some functionalities like transfer funds between accounts, applied interest rate and penalty fees stablished.
Each account has it's own caracteristics like minimum balance, interest rate or penalty fees, it depends on the type of account.

Admin has the ability to create account holders and controlling all the accounts.
Third party is allowed to send and receive transfers from the account holders.

Includes unit and integration tests.




