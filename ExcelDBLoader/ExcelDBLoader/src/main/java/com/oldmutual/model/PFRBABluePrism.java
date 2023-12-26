package com.oldmutual.model;

public class PFRBABluePrism {
    private String transactionDate;
    private String intermediaryNumber;
    private String intermediaryChannel;
    private String clientSurname;
    private String clientName;
    private String clientID;
    private String clientInitials;
    private String productProvider;
    private String productCode;
    private String contractNumber;
    private String businessException;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getIntermediaryChannel() {
        return intermediaryChannel;
    }

    public void setIntermediaryChannel(String intermediaryChannel) {
        this.intermediaryChannel = intermediaryChannel;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getIntermediaryNumber() {
        return intermediaryNumber;
    }

    public void setIntermediaryNumber(String intermediaryNumber) {
        this.intermediaryNumber = intermediaryNumber;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientInitials() {
        return clientInitials;
    }

    public void setClientInitials(String clientInitials) {
        this.clientInitials = clientInitials;
    }

    public String getProductProvider() {
        return productProvider;
    }

    public void setProductProvider(String productProvider) {
        this.productProvider = productProvider;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBusinessException() {
        return businessException;
    }

    public void setBusinessException(String businessException) {
        this.businessException = businessException;
    }
}
