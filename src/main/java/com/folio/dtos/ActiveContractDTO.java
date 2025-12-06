package com.folio.dtos;

import com.folio.models.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActiveContractDTO {
    private Contract contract;
    private Long remainingDays;
}
