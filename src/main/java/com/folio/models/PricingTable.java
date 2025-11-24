package com.folio.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pricingTables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableID;

    @Column(columnDefinition = "TEXT")
    private String data;
}