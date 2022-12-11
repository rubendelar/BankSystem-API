package com.BankSystem.BankSystem.Controllers;

import com.BankSystem.BankSystem.Models.Accounts.AccountType;
import com.BankSystem.BankSystem.Models.DTO.ThirdPartyTransferDTO;
import com.BankSystem.BankSystem.Services.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @PatchMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal thirdPartyTransfer(@RequestHeader String hashKey,
                                         @RequestParam ThirdPartyTransferDTO thirdPartyTransferDTO) {
        return thirdPartyService.thirdPartyTransfer(thirdPartyTransferDTO);

    }
}
