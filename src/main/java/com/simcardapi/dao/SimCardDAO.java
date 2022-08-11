package com.simcardapi.dao;

import com.simcardapi.enums.Status;

import java.sql.Timestamp;

public class SimCardDAO {
    private String sim_card_no;
    private String mobileNo;
    private Status status;
    private Timestamp expiryDate;
    private String stateOfRegistration;
    private boolean kyc;
    private String telecomProvider;
    private String fullName;

    public String getSim_card_no() {
        return this.sim_card_no;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Status getStatus() {
        return this.status;
    }

    public Timestamp getExpiryDate() {
        return this.expiryDate;
    }

    public String getStateOfRegistration() {
        return this.stateOfRegistration;
    }

    public boolean isKyc() {
        return this.kyc;
    }

    public String getTelecomProvider() {
        return this.telecomProvider;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setSim_card_no(String sim_card_no) {
        this.sim_card_no = sim_card_no;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setStateOfRegistration(String stateOfRegistration) {
        this.stateOfRegistration = stateOfRegistration;
    }

    public void setKyc(boolean kyc) {
        this.kyc = kyc;
    }

    public void setTelecomProvider(String telecomProvider) {
        this.telecomProvider = telecomProvider;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
