package com.simcardapi.repository;

import com.simcardapi.entity.RechargeRequest;
import com.simcardapi.enums.ValidationStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RechargeRequestRepository extends CrudRepository<RechargeRequest,String> {
    public List<RechargeRequest> findAllByValidationStatusEquals(ValidationStatus validationStatus);
}
