package com.simcardapi.schedular;

import com.simcardapi.entity.RechargeRequest;
import com.simcardapi.enums.ValidationStatus;
import com.simcardapi.repository.RechargeRequestRepository;
import com.simcardapi.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ValidationSchedular {
    Logger logger = LoggerFactory.getLogger(ValidationSchedular.class);

    @Autowired
    RechargeRequestRepository rechargeRequestRepository;

    @Autowired
    ValidatorService validatorService;

    @Scheduled(fixedDelay = 2000)
    public void validateRechargeRequest(){
        logger.info("Checking for active recharge requests");
        rechargeRequestRepository.findAllByValidationStatusEquals(ValidationStatus.PENDING)
                .forEach(req->{
                    try {
                        System.out.println("Validating request : "+req.getId());
                        validatorService.validate(req.getId());
                    }
                    catch (Exception e){
                        System.out.println("Failed to validate request : "+req.getId()+" with error :" +e.getMessage());
                    }
                });
    }
}
