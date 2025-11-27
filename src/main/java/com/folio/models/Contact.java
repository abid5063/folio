package com.folio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String institution;

    @Column(length = 20)
    private String designation;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 20)
    private String phone2;

    @Column(length = 50)
    private String email;

    @Column(length = 20)
    private String whatsapp;

    @Column(nullable = false)
    private Boolean isCurrent = true;

    @Column(length = 200)
    private String relevantDept;

    @Column(length = 1000)
    private String extraInfo;

}
