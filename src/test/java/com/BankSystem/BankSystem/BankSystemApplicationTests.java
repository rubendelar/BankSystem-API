package com.BankSystem.BankSystem;

import com.BankSystem.BankSystem.Repositories.Accounts.CheckingRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.CreditCardRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.SavingsRepository;
import com.BankSystem.BankSystem.Repositories.Accounts.StudentCheckingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class BankSystemApplicationTests {


	@Autowired
	private WebApplicationContext context;

	@Autowired
	private CheckingRepository checkingRepository;
	private CreditCardRepository creditCardRepository;
	private SavingsRepository savingsRepository;
	private StudentCheckingRepository studentCheckingRepository;


	MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}



}
