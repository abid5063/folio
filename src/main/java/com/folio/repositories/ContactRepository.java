package com.folio.repositories;

import com.folio.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("""
        SELECT c FROM Contact c
        WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
        AND LOWER(c.institution) LIKE LOWER(CONCAT('%', :institution, '%'))
        AND LOWER(c.designation) LIKE LOWER(CONCAT('%', :designation, '%'))
        AND LOWER(c.relevantDept) LIKE LOWER(CONCAT('%', :relevantDept, '%'))
    """)
    List<Contact> filterContacts(
            @Param("name") String name,
            @Param("institution") String institution,
            @Param("designation") String designation,
            @Param("relevantDept") String relevantDept);


}
