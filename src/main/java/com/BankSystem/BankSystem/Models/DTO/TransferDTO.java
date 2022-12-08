package com.BankSystem.BankSystem.Models.DTO;

import java.math.BigDecimal;

public class TransferDTO {

    private Integer sendingId;
    private Integer receivingId;
    private String receivingOwnersName;
    private BigDecimal transferFunds;

    public TransferDTO(Integer sendingId, Integer receivingId, String receivingOwnersName, BigDecimal transferFunds) {
        this.sendingId = sendingId;
        this.receivingId = receivingId;
        this.receivingOwnersName = receivingOwnersName;
        this.transferFunds = transferFunds;
    }

    public Integer getSendingId() {
        return sendingId;
    }

    public void setSendingId(Integer sendingId) {
        this.sendingId = sendingId;
    }

    public Integer getReceivingId() {
        return receivingId;
    }

    public void setReceivingId(Integer receivingId) {
        this.receivingId = receivingId;
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
