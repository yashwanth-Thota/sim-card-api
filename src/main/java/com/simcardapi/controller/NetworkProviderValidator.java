package com.simcardapi.controller;

import com.simcardapi.repository.RechargeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetworkProviderValidator {

    @Autowired
    private RechargeRequestRepository rechargeRequestRepository;

    @PostMapping("/{requestId}/validate")
    public ResponseEntity<String> validatePlan(@PathVariable("requestId") String requestId) throws Exception{


        return new ResponseEntity("requestId : "+requestId+" successfully processed!!", HttpStatus.OK);
    }
}
