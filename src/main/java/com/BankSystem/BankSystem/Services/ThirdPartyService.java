package com.BankSystem.BankSystem.Services;

import com.BankSystem.BankSystem.Repositories.Users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public void thirdPartyTransfer (Integer thirdPartyId, Integer receivingAccountId, String AccountSecretKey, BigDecimal transferFunds) {

    }
}
