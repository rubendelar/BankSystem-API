package com.BankSystem.BankSystem;

import com.BankSystem.BankSystem.Models.Accounts.*;
import com.BankSystem.BankSystem.Models.DTO.CheckingStudentCheckingAccountCreationDTO;
import com.BankSystem.BankSystem.Repositories.Accounts.*;
import com.BankSystem.BankSystem.Models.Users.AccountHolders;
import com.BankSystem.BankSystem.Models.Users.Address;
import com.BankSystem.BankSystem.Models.Users.Admins;
import com.BankSystem.BankSystem.Models.Users.ThirdParty;
import com.BankSystem.BankSystem.Repositories.Users.AccountHoldersRepository;
import com.BankSystem.BankSystem.Repositories.Users.AdminsRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminsControllerTest {


    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository  creditCardRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AdminsRepository adminsRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();




Savings savingAccount;
CreditCard creditCardAccount;
Checking checkingAccount;
StudentChecking studentCheckingAccount;
Admins admin;
AccountHolders primaryOwner;
ThirdParty thirdPartyUser;
CheckingStudentCheckingAccountCreationDTO checkingStudentCheckingAccountCreationDTO;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        admin = new Admins("High Commander", "pass123");
        adminsRepository.save(admin);

        primaryOwner = new AccountHolders("Mozart","pass123", LocalDate.of(2000,10,10), new Address("Calle Emperador", "España"), null );
        accountHoldersRepository.save(primaryOwner);

        savingAccount= new Savings(primaryOwner,null, BigDecimal.valueOf(10000),"pepeymaria123",null,null);
        savingsRepository.save(savingAccount);

        creditCardAccount= new CreditCard(primaryOwner, null,BigDecimal.valueOf(400), "harrypotter123", null, null);
        creditCardRepository.save(creditCardAccount);

        checkingAccount = new Checking(primaryOwner, null,BigDecimal.valueOf(400), "lordOfTheRings123");
        checkingRepository.save(checkingAccount);

        studentCheckingAccount = new StudentChecking(primaryOwner, null,BigDecimal.valueOf(50000), "lordOfTheRings123");
        studentCheckingRepository.save(studentCheckingAccount);

        thirdPartyUser = new ThirdParty("ThirdPartyUserName");
        thirdPartyRepository.save(thirdPartyUser);

        checkingStudentCheckingAccountCreationDTO = new CheckingStudentCheckingAccountCreationDTO(primaryOwner.getId(), null, BigDecimal.valueOf(50000), "tibia");


    }

    @AfterEach
    void tearDown() {
        accountHoldersRepository.deleteAll();
        savingsRepository.deleteAll();
        creditCardRepository.deleteAll();
        checkingRepository.deleteAll();
        adminsRepository.deleteAll();
        thirdPartyRepository.deleteAll();
        studentCheckingRepository.deleteAll();
    }

    @Test

    void getAnyAccountBalance() throws Exception {

        MvcResult result = mockMvc.perform(get("/account-balance")
                        .param("id", String.valueOf(savingAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("10000"));
    }

    @Test
    void  setAnyAccountBalance() throws Exception {
        MvcResult result = mockMvc.perform(patch("/account-setBalance")
                        .param("fund", String.valueOf(1000000))
                        .param("id", String.valueOf(savingAccount.getId()))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1000000"));
    }


    @Test
    void createSavingsAccount() throws Exception {
        String body = objectMapper.writeValueAsString(savingAccount);
        MvcResult result = mockMvc.perform(post("/savingsAccount-creation")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();


        assertTrue(result.getResponse().getContentAsString().contains("10000"));

    }
    @Test
    void createCreditCardAccount() throws Exception {
        String body = objectMapper.writeValueAsString(creditCardAccount);
        MvcResult result = mockMvc.perform(post("/creditCardAccount-creation")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();
        System.err.println(body);

        assertTrue(result.getResponse().getContentAsString().contains("Mozart"));

    }

    @Test
    void createCheckingAccount() throws Exception {
        String body = objectMapper.writeValueAsString(checkingStudentCheckingAccountCreationDTO);
        MvcResult result = mockMvc.perform(post("/checkingAccount-creation")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Mozart"));

    }


    @Test
    void createStudentCheckingAccount() throws Exception {
        String body = objectMapper.writeValueAsString(studentCheckingAccount);
        MvcResult result = mockMvc.perform(post("/directStudentCheckingAccount-creation")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("50000"));

    }


    @Test
    void createThirdPartyUser() throws Exception {
        MvcResult result = mockMvc.perform(post("/thirdParty-creation")
                .param("name","partyUserCreated_Name"))
                .andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("partyUserCreated_Name"));

        }

    @Test
    void changeThirdPartyUsersName() throws Exception {
        MvcResult result = mockMvc.perform(patch("/thirdParty-setter")
                        .param("newName", "Rodolfo" )
                        .param("id", String.valueOf(thirdPartyUser.getId())))

                        .andExpect(status().isAccepted()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Rodolfo"));

    }


    @Test
    void deleteAccount() throws Exception {
        MvcResult result = mockMvc.perform(delete("/account-deletion")
                .param("id", String.valueOf(savingAccount.getId())))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());

    }

    @Test
    void deleteThirdPartyUser() throws Exception {
        MvcResult result = mockMvc.perform(delete("/thirdParty-deletion")
                        .param("id", String.valueOf(thirdPartyUser.getId())))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());

    }

}











