package com.BankSystem.BankSystem;

import com.BankSystem.BankSystem.Controllers.AccountHoldersController;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.Address;
import com.BankSystem.BankSystem.Models.Users.Admins;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

@SpringBootTest
public class AccountHoldersControllerTest {

    @Autowired
    AccountHoldersController accountHoldersController;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //objectMapper.findAndRegisterModules();

        AccountHolders accountHolder = new AccountHolders("Pepe", "pass123", LocalDate.of(1000,01,01), new Address("aksjd","lakjsd"), "pepe@pepehone.com");
//        accountHoldersRepository.save(accountHolder);

    }

    @AfterEach
    void tearDown() {
        accountHoldersRepository.deleteAll();

    }

    @Test
    void get_accounts() throws Exception {

    }


}
