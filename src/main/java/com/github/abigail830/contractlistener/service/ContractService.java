package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import com.github.abigail830.contractlistener.repository.ContractAuditTrailRepository;
import com.github.abigail830.contractlistener.repository.ContractRepository;
import com.github.abigail830.contractlistener.util.JenkinsProperties;
import com.github.abigail830.contractlistener.util.JenkinsTrigger;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private static final Logger logger = LoggerFactory.getLogger(ContractService.class);

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractAuditTrailRepository contractAuditTrailRepository;

    @Autowired
    JenkinsProperties jenkinsProperties;

    public ContractDTO addContract(ContractDTO contractDTO){
        Contract contract = contractDTO.convertToContractEntity();
        contractRepository.save(contract);

        logger.info("Adding audit trail for request.");
        contractDTO.setId(contract.getId());
        ContractAuditTrail contractAuditTrail = contractDTO.convertToAuditTrailEntity();
        contractAuditTrail.setLastModifiedAction("ADD");
        contractAuditTrailRepository.save(contractAuditTrail);

        if (!jenkinsProperties.isSkip())
            JenkinsTrigger.build(jenkinsProperties.getUrl());

        //query back the latest image from DB
        return new ContractDTO(contract);
    }

    public ContractDTO updateContract(ContractDTO contractDTO){
        Contract contract = contractDTO.convertToContractEntity();
        contractRepository.save(contract);

        logger.info("Adding audit trail for UPDATE request.");
        ContractAuditTrail contractAuditTrail = contractDTO.convertToAuditTrailEntity();
        contractAuditTrail.setLastModifiedAction("UPDATE");
        contractAuditTrailRepository.save(contractAuditTrail);

        if (!jenkinsProperties.isSkip())
            JenkinsTrigger.build(jenkinsProperties.getUrl());

        //return the current
        return contractDTO;
    }

    public void deleteContract(String id){
        contractRepository.deleteById(id);

        logger.info("Adding audit trail for DELETE request.");
        ContractAuditTrail contractAuditTrail = new ContractAuditTrail();
        contractAuditTrail.setContractID(id);
        contractAuditTrail.setLastModifiedAction("DELETE");
        contractAuditTrailRepository.save(contractAuditTrail);

        if (!jenkinsProperties.isSkip())
            JenkinsTrigger.build(jenkinsProperties.getUrl());

    }


    public List<ContractDTO> getAllContract(){
        return contractRepository.findAll().stream().map(ContractDTO::new).collect(Collectors.toList());
    }


    public ContractDTO getContractDomainById(String id){
        return contractRepository.findById(id).map(contract -> new ContractDTO(contract)).orElse(null);
    }

    public List<ContractDTO> getContractDomainByUrl(String url){
        return contractRepository.findByApi(url)
                .stream().map(ContractDTO::new).collect(Collectors.toList());
    }

    public List<ContractDTO> getContractDomainByProviderInfo(String providerSystem, String providerName){

        if(StringUtils.isNotBlank(providerName) && StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystemAndProviderID(providerSystem, providerName)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerName)){
            return contractRepository.findByProviderID(providerName)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystem(providerSystem)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else{
            return contractRepository.findAll()
                    .stream().map(ContractDTO::new).collect(Collectors.toList());
        }

    }


    public List<ContractDTO> getContractsByExample(String consumerSystem, String consumerID,
                                                   String providerSystem, String providerID,
                                                   String api, String method, String contractType) {
        Contract contract = new Contract();
        contract.setConsumerSystem(consumerSystem);
        contract.setConsumerID(consumerID);
        contract.setProviderSystem(providerSystem);
        contract.setProviderID(providerID);
        contract.setApi(api);
        contract.setMethod(method);
        contract.setContractType(contractType);
        logger.info("Probe Contract to be filter is {}.", contract);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "request", "response", "desc");

        return contractRepository.findAll(Example.of(contract, matcher))
                .stream().map(ContractDTO::new).collect(Collectors.toList());
    }
}
