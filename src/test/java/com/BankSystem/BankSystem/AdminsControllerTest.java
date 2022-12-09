package com.BankSystem.BankSystem;

import com.BankSystem.BankSystem.Controllers.AccountHoldersController;
import com.BankSystem.BankSystem.Controllers.AdminsController;
import com.BankSystem.BankSystem.Models.Accounts.Savings;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.Admins;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

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
    SavingsRepository savingsRepository;
    @Autowired
    AdminsRepository adminsRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    Savings savingAccount;

    Admins admin;



    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //objectMapper.findAndRegisterModules();

        admin = new Admins("High Commander", "pass123");
        AccountHolders primaryOwner = new AccountHolders();
        Savings savingAccount= new Savings(primaryOwner,null, BigDecimal.valueOf(50000),"pepeymaria123",BigDecimal.valueOf(200),BigDecimal.valueOf(0.4));
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


        String body = objectMapper.writeValueAsString(admin);
        MvcResult result = mockMvc.perform(get("/account-balance")
                        .param("id", body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("200"));
    }

    @Test
    void createThirdPartyUser() throws Exception {
        MvcResult result = mockMvc.perform(post("/thirdParty-creation")
                .param("name","partyUserCreated_Name")).andExpect(status().isAccepted()).andReturn();

        //assertTrue(result.getResponse().getContentAsString().contains(thirdPartyRepository.findAll().get());
        assertTrue(thirdPartyRepository.findByName("partyUserCreated_Name").isPresent());
        }


    //    @PostMapping("/thirdParty-creation")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ThirdParty createThirdPartyUser(@RequestParam String name) {
//        return adminService.createThirdPartyUser(name);
//    }
}











