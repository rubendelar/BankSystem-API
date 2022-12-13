package com.BankSystem.BankSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BankSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BigDecimal a = new BigDecimal(3);
		BigDecimal b = new BigDecimal(2);
		System.out.println("--------------------------- " + a.compareTo(b));

	}
}
