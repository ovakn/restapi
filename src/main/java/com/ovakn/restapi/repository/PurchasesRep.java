package com.ovakn.restapi.repository;

import com.ovakn.restapi.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRep extends JpaRepository<Purchase, Integer> {
}