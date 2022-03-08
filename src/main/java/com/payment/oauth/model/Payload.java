package com.payment.oauth.model;

public class Payload {

    private Beneficiary beneficiary;
    private String creationDateTime;
    private CreditTransferTransaction creditTransferTransaction;
    private int numberOfTransactions;
    private String paymentInformationId;
    private PaymentTypeInformation paymentTypeInformation;

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public CreditTransferTransaction getCreditTransferTransaction() {
        return creditTransferTransaction;
    }

    public void setCreditTransferTransaction(CreditTransferTransaction creditTransferTransaction) {
        this.creditTransferTransaction = creditTransferTransaction;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public String getPaymentInformationId() {
        return paymentInformationId;
    }

    public void setPaymentInformationId(String paymentInformationId) {
        this.paymentInformationId = paymentInformationId;
    }

    public PaymentTypeInformation getPaymentTypeInformation() {
        return paymentTypeInformation;
    }

    public void setPaymentTypeInformation(PaymentTypeInformation paymentTypeInformation) {
        this.paymentTypeInformation = paymentTypeInformation;
    }
}
