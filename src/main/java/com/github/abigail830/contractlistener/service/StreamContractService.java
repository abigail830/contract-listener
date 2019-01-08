package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.StreamContractDTO;
import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.repository.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamContractService {

    private static final Logger logger = LoggerFactory.getLogger(StreamContractService.class);

    @Autowired
    ContractRepository contractRepository;

    public List<StreamContractDTO> getAllStreamContract(){
        return contractRepository.findAll().stream().map(StreamContractDTO::new).collect(Collectors.toList());
    }

    public List<StreamContractDTO> getStreamContractByExample(String providerSystem, String providerID,
                                                              String consumerSystem, String consumerID) {
        Contract contract = new Contract();
        contract.setConsumerSystem(consumerSystem);
        contract.setConsumerID(consumerID);
        contract.setProviderSystem(providerSystem);
        contract.setProviderID(providerID);
        logger.info("Probe Contract to be filter is {}.", contract);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "api", "method", "contractType", "request", "response", "desc");

        return contractRepository.findAll(Example.of(contract, matcher))
                .stream().map(StreamContractDTO::new).collect(Collectors.toList());
    }
}
