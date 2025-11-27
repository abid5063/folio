package com.folio.dtos;

import lombok.Data;

@Data
public class ContactFilterDTO {
    private String name;
    private String institution;
    private String designation;
    private String relevantDept;
}
