package com.simcardapi.repository;

import com.simcardapi.entity.SimCard;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface SimCardRepository extends CrudRepository<SimCard, String> {
    public Optional<SimCard> findByMobileNo(String mobileNo);
    public List<SimCard> findByExpiryDateGreaterThanAndExpiryDateLessThan(Timestamp today,Timestamp next30Days);
}
