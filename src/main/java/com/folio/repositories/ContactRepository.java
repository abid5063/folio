package com.folio.repositories;

import com.folio.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("""
            SELECT c FROM Contact c
            WHERE (:name IS NULL OR LOWER(c.name) = LOWER(:name))
            AND (:institution IS NULL OR LOWER(c.institution) = LOWER(:institution))
            AND (:designation IS NULL OR LOWER(c.designation) = LOWER(:designation))
            AND (:relevantDept IS NULL OR LOWER(c.relevantDept) LIKE LOWER(CONCAT('%', :relevantDept, '%')))
        """)
    List<Contact> filterContacts(
            @Param("name") String name,
            @Param("institution") String institution,
            @Param("designation") String designation,
            @Param("relevantDept") String relevantDept
    );
}
