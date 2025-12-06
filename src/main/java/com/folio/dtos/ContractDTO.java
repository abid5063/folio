package com.folio.dtos;

import lombok.Data;

@Data
public class ContractDTO {

    private String agreementNo;
    private String agreementName;
    private String signingDate;
    private String startDate;
    private String endingDate;
    private String companyRegNo;
    private String registrationAddress;
    private String administrativeAddress;
    private String operationAddress;
    private Integer bsclSignatoryId;
    private String contractorSignatory;
    private String contact1;
    private String contact2;
    private String contact3;
    private Integer totalPrice;
    private String securityDeposit;
    private String bankAccNo;
    private String emergencyContact1;
    private String emergencyContact2;
    private String emergencyContact3;
    private String emergencyContact4;
    private String contractDownloadLink;
    private String customization;

}
