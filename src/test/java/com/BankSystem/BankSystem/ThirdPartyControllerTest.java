package com.BankSystem.BankSystem;


import com.BankSystem.BankSystem.Models.Accounts.Savings;
import com.BankSystem.BankSystem.Models.DTO.ThirdPartyTransferDTO;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.Address;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartyControllerTest {

    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    ThirdPartyTransferDTO thirdPartyTransferDTO;
    AccountHolders primaryOwner;
    Savings savingAccount;

    ThirdParty thirdParty;

    @BeforeEach
    void setup () {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        thirdParty = new ThirdParty("Il Capo");
        thirdPartyRepository.save(thirdParty);

        primaryOwner = new AccountHolders("Mozart","pass123", LocalDate.of(2000,10,10), new Address("Calle Emperador", "España"), null );
        accountHoldersRepository.save(primaryOwner);

        savingAccount= new Savings(primaryOwner,null, BigDecimal.valueOf(400),"pepeymaria123",null,null);
        savingsRepository.save(savingAccount);

        thirdPartyTransferDTO = new ThirdPartyTransferDTO(thirdParty.getHashKey(),thirdParty.getId(),savingAccount.getId(), savingAccount.getSecretKey(), BigDecimal.valueOf(5000));



    }
    @AfterEach
    void tearDown () {
        accountHoldersRepository.deleteAll();
        savingsRepository.deleteAll();
        thirdPartyRepository.deleteAll();
    }

    @Test
    void transfer() throws Exception {
        String body = objectMapper.writeValueAsString(thirdPartyTransferDTO);
        MvcResult result = mockMvc.perform(patch("/transfer")
                        .header(thirdParty.getHashKey())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isAccepted()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("5400"));

    }





}
