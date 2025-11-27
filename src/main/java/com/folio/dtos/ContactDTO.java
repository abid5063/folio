package com.folio.dtos;

import lombok.Data;

@Data
public class ContactDTO {

    private String name;
    private String institution;
    private String designation;
    private String phone;
    private String phone2;
    private String email;
    private String whatsapp;
    private Boolean isCurrent;
    private String relevantDept;
    private String extraInfo;
}
