package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.ContractAuditTrailDTO;
import com.github.abigail830.contractlistener.repository.ContractAuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractAuditTrailService {

    @Autowired
    ContractAuditTrailRepository contractAuditTrailRepository;

    public List<ContractAuditTrailDTO> getContractDomainByUrl(String url){
        contractAuditTrailRepository.findByApi(url).stream().forEach(System.out::println);

        return contractAuditTrailRepository.findByApi(url)
                .stream().map(ContractAuditTrailDTO::new).collect(Collectors.toList());
    }

    public List<ContractAuditTrailDTO> getAllContractAuditTrail(){
        return contractAuditTrailRepository.findAll()
                .stream().map(ContractAuditTrailDTO::new).collect(Collectors.toList());
    }

}
