package com.folio.repositories;

import com.folio.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query("""
        SELECT c FROM Contract c
        WHERE c.startDate <= CURRENT_DATE
          AND c.endingDate >= CURRENT_DATE
    """)
    List<Contract> findActiveContracts();

}
