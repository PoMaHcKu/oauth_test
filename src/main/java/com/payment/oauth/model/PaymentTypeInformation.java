package com.payment.oauth.model;

public class PaymentTypeInformation {

    private String categoryPurpose;
    private String localInstrument;
    private String serviceLevel;

    public String getCategoryPurpose() {
        return categoryPurpose;
    }

    public void setCategoryPurpose(String categoryPurpose) {
        this.categoryPurpose = categoryPurpose;
    }

    public String getLocalInstrument() {
        return localInstrument;
    }

    public void setLocalInstrument(String localInstrument) {
        this.localInstrument = localInstrument;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
}
