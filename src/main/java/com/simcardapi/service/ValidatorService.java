package com.simcardapi.service;

import com.simcardapi.entity.RechargeRequest;
import com.simcardapi.enums.ValidationStatus;
import com.simcardapi.repository.RechargeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class ValidatorService {
    @Autowired
    RechargeRequestRepository rechargeRequestRepository;
    public boolean validate(String requestId) throws  Exception{
        if(!rechargeRequestRepository.findById(requestId).isPresent()){
            throw new Exception("requestId :"+requestId+" doesn't exists");
        }
        RechargeRequest rechargeRequest= rechargeRequestRepository.findById(requestId).get();
        if(rechargeRequestRepository.findById(requestId).get().getCreatedTimestamp().after(Timestamp.from(Instant.now().plus(30, ChronoUnit.MINUTES)))){
            rechargeRequest.setValidationStatus(ValidationStatus.EXPIRED.value);
            rechargeRequestRepository.save(rechargeRequest);
            throw new Exception("requestId :"+requestId+" already expired!!");
        }

        rechargeRequest.setValidationStatus(ValidationStatus.VERIFIED.value);
        rechargeRequestRepository.save(rechargeRequest);
        return true;
    }
}
