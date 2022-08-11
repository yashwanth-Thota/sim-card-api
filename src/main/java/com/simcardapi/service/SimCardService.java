package com.simcardapi.service;

import com.simcardapi.entity.RechargeRequest;
import com.simcardapi.entity.SimCard;
import com.simcardapi.enums.ValidationStatus;
import com.simcardapi.repository.RechargeRequestRepository;
import com.simcardapi.repository.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimCardService {

    @Autowired
    private SimCardRepository simCardRepository;
    @Autowired
    private RechargeRequestRepository rechargeRequestRepository;

    public List<SimCard> getAll(){
        return StreamSupport.stream(simCardRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public SimCard addNewCard(SimCard simCard) throws Exception{
        if(simCardRepository.findByMobileNo(simCard.getMobileNo()).isPresent()){
            throw new Exception("simcard with mobile number : "+simCard.getMobileNo()+" is already registered with other user");
        }
        return simCardRepository.save(simCard);
    }

    public boolean updateCard(String id, SimCard simCardDetails) throws Exception{
        if(!simCardRepository.findById(id).isPresent()){
            throw new Exception("simcard with id : "+id+" is not present");
        }
        SimCard temp=simCardRepository.findById(id).get();
        if(simCardDetails.getExpiryDate()!=null)
            temp.setExpiryDate(simCardDetails.getExpiryDate().toString());
        if(simCardDetails.isKyc())
            temp.setKyc(simCardDetails.isKyc());
        if(simCardDetails.getTelecomProvider()!=null)
            temp.setTelecomProvider(simCardDetails.getTelecomProvider());
        if(simCardDetails.getStatus()!=null)
            temp.setStatus(simCardDetails.getStatus().value);
        simCardRepository.save(temp);
        return true;
    }

    public boolean delete(String id) throws Exception{
        if(!simCardRepository.findById(id).isPresent()){
            throw new Exception("simcard with id : "+id+" is not present");
        }
        simCardRepository.deleteById(id);
        return true;
    }

    public List<SimCard> getAllToRenew(){
        return simCardRepository.findByExpiryDateGreaterThanAndExpiryDateLessThan(Timestamp.from(Instant.now()),Timestamp.from(Instant.now().plus(30, ChronoUnit.DAYS)));
    }

    public String renew(String id,String plan) throws Exception {
        if(!simCardRepository.findById(id).isPresent()){
            throw new Exception("simcard with id : "+id+" is not present");
        }
        SimCard temp=simCardRepository.findById(id).get();
        RechargeRequest rr=new RechargeRequest();
        rr.setSimCard(temp);
        rr.setValidationStatus(ValidationStatus.PENDING.value);
        rr.setCreatedTimestamp(Timestamp.from(Instant.now()));
        rr.setPlan(plan);

        rr=rechargeRequestRepository.save(rr);

        return rr.getId();
    }
}
