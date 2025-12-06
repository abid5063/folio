package com.folio.services;

import com.folio.dtos.ActiveContractDTO;
import com.folio.dtos.ContractDTO;
import com.folio.models.Contract;
import com.folio.repositories.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    // -------------------- ADD a new contract --------------------
    public Contract addContract(ContractDTO dto) {
        Contract contract = new Contract();

        contract.setAgreementNo(dto.getAgreementNo());
        contract.setAgreementName(dto.getAgreementName());
        contract.setSigningDate(parseDate(dto.getSigningDate()));
        contract.setStartDate(parseDate(dto.getStartDate()));
        contract.setEndingDate(parseDate(dto.getEndingDate()));
        contract.setCompanyRegNo(dto.getCompanyRegNo());
        contract.setRegistrationAddress(dto.getRegistrationAddress());
        contract.setAdministrativeAddress(dto.getAdministrativeAddress());
        contract.setOperationAddress(dto.getOperationAddress());
        contract.setBsclSignatoryId(dto.getBsclSignatoryId());
        contract.setContractorSignatory(dto.getContractorSignatory());
        contract.setContact1(dto.getContact1());
        contract.setContact2(dto.getContact2());
        contract.setContact3(dto.getContact3());
        contract.setTotalPrice(dto.getTotalPrice());
        contract.setSecurityDeposit(dto.getSecurityDeposit());
        contract.setBankAccNo(dto.getBankAccNo());
        contract.setEmergencyContact1(dto.getEmergencyContact1());
        contract.setEmergencyContact2(dto.getEmergencyContact2());
        contract.setEmergencyContact3(dto.getEmergencyContact3());
        contract.setEmergencyContact4(dto.getEmergencyContact4());
        contract.setContractDownloadLink(dto.getContractDownloadLink());
        contract.setCustomization(dto.getCustomization());

        return contractRepository.save(contract);
    }

    // -------------------- UPDATE existing contract --------------------
    public Contract updateContract(Long id, ContractDTO dto) {
        Optional<Contract> optional = contractRepository.findById(id);

        if (optional.isEmpty()) {
            return null; // not found
        }

        Contract contract = optional.get();

        if (dto.getAgreementNo() != null) contract.setAgreementNo(dto.getAgreementNo());
        if (dto.getAgreementName() != null) contract.setAgreementName(dto.getAgreementName());
        if (dto.getSigningDate() != null) contract.setSigningDate(parseDate(dto.getSigningDate()));
        if (dto.getStartDate() != null) contract.setStartDate(parseDate(dto.getStartDate()));
        if (dto.getEndingDate() != null) contract.setEndingDate(parseDate(dto.getEndingDate()));
        if (dto.getCompanyRegNo() != null) contract.setCompanyRegNo(dto.getCompanyRegNo());
        if (dto.getRegistrationAddress() != null) contract.setRegistrationAddress(dto.getRegistrationAddress());
        if (dto.getAdministrativeAddress() != null) contract.setAdministrativeAddress(dto.getAdministrativeAddress());
        if (dto.getOperationAddress() != null) contract.setOperationAddress(dto.getOperationAddress());
        if (dto.getBsclSignatoryId() != null) contract.setBsclSignatoryId(dto.getBsclSignatoryId());
        if (dto.getContractorSignatory() != null) contract.setContractorSignatory(dto.getContractorSignatory());
        if (dto.getContact1() != null) contract.setContact1(dto.getContact1());
        if (dto.getContact2() != null) contract.setContact2(dto.getContact2());
        if (dto.getContact3() != null) contract.setContact3(dto.getContact3());
        if (dto.getTotalPrice() != null) contract.setTotalPrice(dto.getTotalPrice());
        if (dto.getSecurityDeposit() != null) contract.setSecurityDeposit(dto.getSecurityDeposit());
        if (dto.getBankAccNo() != null) contract.setBankAccNo(dto.getBankAccNo());
        if (dto.getEmergencyContact1() != null) contract.setEmergencyContact1(dto.getEmergencyContact1());
        if (dto.getEmergencyContact2() != null) contract.setEmergencyContact2(dto.getEmergencyContact2());
        if (dto.getEmergencyContact3() != null) contract.setEmergencyContact3(dto.getEmergencyContact3());
        if (dto.getEmergencyContact4() != null) contract.setEmergencyContact4(dto.getEmergencyContact4());
        if (dto.getContractDownloadLink() != null) contract.setContractDownloadLink(dto.getContractDownloadLink());
        if (dto.getCustomization() != null) contract.setCustomization(dto.getCustomization());

        return contractRepository.save(contract);
    }

    // -------------------- GET all contracts --------------------
    public List<Contract> getAllContract() {
        return contractRepository.findAll();
    }

    // -------------------- GET by ID --------------------
    public Optional<Contract> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    // -------------------- Helper: Parse LocalDate --------------------
    private LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.isBlank()) return null;
        return LocalDate.parse(dateString);
    }

    public List<ActiveContractDTO> getActiveContracts() {
        List<Contract> activeContracts = contractRepository.findActiveContracts();
        LocalDate today = LocalDate.now();

        return activeContracts.stream().map(contract -> {
            long remainingDays = ChronoUnit.DAYS.between(today, contract.getEndingDate());
            return new ActiveContractDTO(contract, remainingDays);
        }).toList();
    }

}
