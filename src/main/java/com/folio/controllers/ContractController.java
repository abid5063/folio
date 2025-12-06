package com.folio.controllers;

import com.folio.dtos.ActiveContractDTO;
import com.folio.dtos.ContractDTO;
import com.folio.models.Contract;
import com.folio.services.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contract")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/all")
    public List<Contract> getAllContracts() {
        return contractService.getAllContract();
    }
    
    @GetMapping("/{id}")
    public Optional<Contract> getContractById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @GetMapping("/active")
    public List<ActiveContractDTO> getActiveContracts() {
        return contractService.getActiveContracts();
    }

    @PostMapping("/update/{id}")
    public Contract updateContract(@PathVariable Long id, @RequestBody ContractDTO contract) {
        return contractService.updateContract(id, contract);
    }

    @PostMapping("/add")
    public boolean addContract(@RequestBody ContractDTO contract) {
        Contract temp = contractService.addContract(contract);
        return !(temp == null);
    }
}
