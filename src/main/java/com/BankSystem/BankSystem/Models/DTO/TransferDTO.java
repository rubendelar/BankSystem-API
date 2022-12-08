package com.BankSystem.BankSystem.Models.DTO;

import java.math.BigDecimal;

public class TransferDTO {
    //Integer sendingId, Integer receivingId, String receivingOwnersName, BigDecimal transferFunds

    private Integer sendingId;
    private Integer recevinginId;
    private String receivingOwnersName;
    private BigDecimal transferFunds;

    public TransferDTO(Integer sendingId, Integer recevinginId, String receivingOwnersName, BigDecimal transferFunds) {
        this.sendingId = sendingId;
        this.recevinginId = recevinginId;
        this.receivingOwnersName = receivingOwnersName;
        this.transferFunds = transferFunds;
    }

    public Integer getSendingId() {
        return sendingId;
    }

    public void setSendingId(Integer sendingId) {
        this.sendingId = sendingId;
    }

    public Integer getRecevinginId() {
        return recevinginId;
    }

    public void setRecevinginId(Integer recevinginId) {
        this.recevinginId = recevinginId;
    }

    public String getReceivingOwnersName() {
        return receivingOwnersName;
    }

    public void setReceivingOwnersName(String receivingOwnersName) {
        this.receivingOwnersName = receivingOwnersName;
    }

    public BigDecimal getTransferFunds() {
        return transferFunds;
    }

    public void setTransferFunds(BigDecimal transferFunds) {
        this.transferFunds = transferFunds;
    }
}
