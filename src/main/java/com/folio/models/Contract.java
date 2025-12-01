package com.folio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    private String agreementNo;
    private String agreementName;

    private LocalDate signingDate;
    private LocalDate startDate;
    private LocalDate endingDate;

    private String companyRegNo;

    private String registrationAddress;
    private String administrativeAddress;
    private String operationAddress;

    // Foreign key to users.userId
    private Integer bsclSignatoryId;

    private String contractorSignatory;

    private String contact1;
    private String contact2;
    private String contact3;

    private Integer totalPrice;

    private String emergencyContact1;
    private String emergencyContact2;
    private String emergencyContact3;
    private String emergencyContact4;

    private String contractDownloadLink;

    private String customization;
}
