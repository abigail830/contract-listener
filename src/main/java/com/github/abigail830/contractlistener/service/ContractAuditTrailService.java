package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.ContractAuditTrailDTO;
import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import com.github.abigail830.contractlistener.repository.ContractAuditTrailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractAuditTrailService {

    private static final Logger logger = LoggerFactory.getLogger(ContractAuditTrailService.class);

    @Autowired
    ContractAuditTrailRepository contractAuditTrailRepository;


    public List<ContractAuditTrailDTO> getAllContractAuditTrail(){
        return contractAuditTrailRepository.findAll()
                .stream().map(ContractAuditTrailDTO::new).collect(Collectors.toList());
    }

    public List<ContractAuditTrailDTO> getContractAuditTrailByCriteria(String contractID,
                                                                       String lastModifiedAction,
                                                                       String lastModifiedBy) {
        ContractAuditTrail contractAuditTrail = new ContractAuditTrail();
        contractAuditTrail.setContractID(contractID);
        contractAuditTrail.setLastModifiedAction(lastModifiedAction);
        contractAuditTrail.setLastModifiedBy(lastModifiedBy);
        logger.info("Probe AuditTrail to be filter is {}.", contractAuditTrail);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "providerSystem", "providerID", "consumerSystem", "consumerID",
                        "api", "method", "contractType", "request", "response", "desc", "lastModified");

        return contractAuditTrailRepository.findAll(Example.of(contractAuditTrail, matcher))
                .stream().map(ContractAuditTrailDTO::new).collect(Collectors.toList());
    }
}
