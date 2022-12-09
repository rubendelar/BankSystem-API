package com.BankSystem.BankSystem;

import com.BankSystem.BankSystem.Controllers.AccountHoldersController;
import com.BankSystem.BankSystem.Controllers.AdminsController;
import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.Accounts.Savings;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.Address;
import com.BankSystem.BankSystem.Models.Users.Admins;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.AccountTypeRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.BankSystem.BankSystem.Repositories.Users.AdminsRepository;
import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminsControllerTest {

    @Autowired
    AdminsController adminsController;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    AdminsRepository adminsRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();




Savings savingAccount;
Admins admin;

AccountHolders primaryOwner;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //objectMapper.findAndRegisterModules();

        admin = new Admins("High Commander", "pass123");
        primaryOwner = new AccountHolders("Mozart","pass123", LocalDate.of(2000,10,10), new Address("Calle Emperador", "España"), null );
        savingAccount= new Savings(primaryOwner,null, BigDecimal.valueOf(200),"pepeymaria123",BigDecimal.valueOf(200),BigDecimal.valueOf(0.3));

        accountHoldersRepository.save(primaryOwner);
        savingsRepository.save(savingAccount);
        adminsRepository.save(admin);

    }

    @AfterEach
    void tearDown() {
        savingsRepository.deleteAll();
        adminsRepository.deleteAll();
    }

    @Test
    void getAnyAccountBalance() throws Exception {

//        String body = objectMapper.writeValueAsString(savingAccount);
        MvcResult result = mockMvc.perform(get("/account-balance")
                        .param("id", String.valueOf(savingAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("200"));
    }

    @Test
    void createSavingsAccount() throws Exception {

        String body = objectMapper.writeValueAsString(savingAccount);
        MvcResult result = mockMvc.perform(get("/savingsAccount-creation")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("200"));

    }



    @Test
    void createThirdPartyUser() throws Exception {
        MvcResult result = mockMvc.perform(post("/thirdParty-creation")
                .param("name","partyUserCreated_Name")).andExpect(status().isCreated()).andReturn();

        assertTrue(thirdPartyRepository.findByName("partyUserCreated_Name").isPresent());
        }

//    @Test
//    void deleteAccount() throws Exception {
//        MvcResult result = mockMvc.perform(delete("/account-deletion")
//                .param("id",savingAccount.getId()))
//                .andExpect(status().isAccepted()).andReturn();
//
//        assertTrue(accountTypeRepository.findById(savingAccount.getId()).isEmpty());
//    }


















}











