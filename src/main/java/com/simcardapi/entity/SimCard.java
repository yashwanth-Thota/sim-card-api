package com.simcardapi.entity;

import com.simcardapi.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SimCard")
public class SimCard {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    private String sim_card_no;
    private String mobileNo;
    private Status status;
    private Timestamp expiryDate;
    private String stateOfRegistration;
    private boolean kyc;
    private String telecomProvider;
    private String fullName;

    public SimCard(){

    }

    public SimCard(String sim_card_no,String mobileNo, String status, String expiryDate, String stateOfRegistration, boolean kyc, String telecomProvider, String fullName) {
        this.sim_card_no=sim_card_no;
        this.mobileNo = mobileNo;
        this.status = Status.getStatus(status);
        this.expiryDate = Timestamp.valueOf(expiryDate);
        this.stateOfRegistration = stateOfRegistration;
        this.kyc = kyc;
        this.telecomProvider = telecomProvider;
        this.fullName = fullName;
    }

    public String getId() {
        return this.id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setStatus(String status) {
        this.status = Status.getStatus(status);
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = Timestamp.valueOf(expiryDate);
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

    public String getSim_card_no() {
        return this.sim_card_no;
    }

    public void setSim_card_no(String sim_card_no) {
        this.sim_card_no = sim_card_no;
    }
}
