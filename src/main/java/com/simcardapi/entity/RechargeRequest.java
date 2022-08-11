package com.simcardapi.entity;

import com.simcardapi.enums.ValidationStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="RechargeRequest")
public class RechargeRequest {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @ManyToOne
    private SimCard simCard;

    private Timestamp createdTimestamp;

    private ValidationStatus validationStatus;

    private String plan;

    public RechargeRequest() {
    }

    public String getId() {
        return this.id;
    }

    public SimCard getSimCard() {
        return this.simCard;
    }

    public Timestamp getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSimCard(SimCard simCard) {
        this.simCard = simCard;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public ValidationStatus getValidationStatus() {
        return this.validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = ValidationStatus.getValidationStatus(validationStatus);
    }

    public String getPlan() {
        return this.plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
