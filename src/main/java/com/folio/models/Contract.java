package com.folio.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @Column(unique = true)
    private String agreementNo;

    private LocalDate startDate;

    private LocalDate endingDate;

    @ManyToOne
    @JoinColumn(name = "bsclSignatoryId")
    private User bsclSignatory;

    private String contractorSignatory;

    @ManyToOne
    @JoinColumn(name = "pricingId")
    private PricingTable pricingTable;

    private String contractDownloadLink;

    @Column(columnDefinition = "TEXT")
    private String customization;
}