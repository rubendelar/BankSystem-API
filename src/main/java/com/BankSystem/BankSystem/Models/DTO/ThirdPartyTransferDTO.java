package com.BankSystem.BankSystem.Models.DTO;

import java.math.BigDecimal;

public class ThirdPartyTransferDTO {


    private String hashKey;

    private Integer thirdPartyId;

    private Integer AccountId;

    private String accountSecretKey;

    private BigDecimal transferFunds;

    public ThirdPartyTransferDTO(String hashKey, Integer thirdPartyId, Integer accountId, String accountSecretKey, BigDecimal transferFunds) {
        this.hashKey = hashKey;
        this.thirdPartyId = thirdPartyId;
        AccountId = accountId;
        this.accountSecretKey = accountSecretKey;
        this.transferFunds = transferFunds;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public Integer getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(Integer thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public Integer getAccountId() {
        return AccountId;
    }

    public void setAccountId(Integer accountId) {
        AccountId = accountId;
    }

    public String getAccountSecretKey() {
        return accountSecretKey;
    }

    public void setAccountSecretKey(String accountSecretKey) {
        this.accountSecretKey = accountSecretKey;
    }

    public BigDecimal getTransferFunds() {
        return transferFunds;
    }

    public void setTransferFunds(BigDecimal transferFunds) {
        this.transferFunds = transferFunds;
    }
}
