package com.BankSystem.BankSystem;

import com.BankSystem.BankSystem.Models.Accounts.*;
import com.BankSystem.BankSystem.Models.DTO.TransferDTO;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.Address;
import com.BankSystem.BankSystem.Repositories.Accounts.*;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHoldersControllerTest {


    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    Savings savingAccount;
    CreditCard creditCardAccount;
    AccountHolders primaryOwner;
    AccountHolders secondaryOwner;
    TransferDTO transferDTO;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        primaryOwner = new AccountHolders("Mozart","pass123", LocalDate.of(2000,10,10), new Address("Calle Emperador", "España"), null );
        accountHoldersRepository.save(primaryOwner);

        secondaryOwner = new AccountHolders("Betwoven","pass321", LocalDate.of(1990,10,10), new Address("Calle Reinaldo", "España"), null );
        accountHoldersRepository.save(secondaryOwner);

        savingAccount= new Savings(primaryOwner,null, BigDecimal.valueOf(10000),"pepeymaria123",null,null);
        savingsRepository.save(savingAccount);

        creditCardAccount= new CreditCard(secondaryOwner, null,BigDecimal.valueOf(10000), "harrypotter123", null, null);
        creditCardRepository.save(creditCardAccount);

        transferDTO = new TransferDTO(creditCardAccount.getId(), savingAccount.getId(), "Mozart", BigDecimal.valueOf(5000));


    }

    @AfterEach
    void tearDown() {
        accountHoldersRepository.deleteAll();
        savingsRepository.deleteAll();
        creditCardRepository.deleteAll();
    }


    @Test
    void getMyAccountInfo() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts")
                        .param("id", String.valueOf(primaryOwner.getId()))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Mozart"));

    }

    @Test
    void getBalance() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts-balance")
                        .param("id", String.valueOf(primaryOwner.getId()))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("10000"));

    }

    @Test
    void transfer() throws Exception {
        String body = objectMapper.writeValueAsString(transferDTO);
        MvcResult result = mockMvc.perform(patch("/accounts-transfer")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("5000"));

    }


}
