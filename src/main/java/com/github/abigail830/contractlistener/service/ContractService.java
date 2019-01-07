package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import com.github.abigail830.contractlistener.repository.ContractAuditTrailRepository;
import com.github.abigail830.contractlistener.repository.ContractRepository;
import com.github.abigail830.contractlistener.util.JenkinsTrigger;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ContractDTO addContract(ContractDTO contractDTO){
        Contract contract = contractDTO.convertToEntity();
        contractRepository.save(contract);

        logger.info("Adding audit trail for request.");
        ContractAuditTrail contractAuditTrail = contractDTO.convertToAuditTrail();
        contractAuditTrailRepository.save(contractAuditTrail);

        JenkinsTrigger.build();

        //query back the latest image from DB
        return null;
    }

    public ContractDTO updateContract(ContractDTO contractDTO){
        Contract contract = contractDTO.convertToEntity();
        contractRepository.save(contract);

        logger.info("Adding audit trail for request.");
        ContractAuditTrail contractAuditTrail = contractDTO.convertToAuditTrail();
        contractAuditTrailRepository.save(contractAuditTrail);

        JenkinsTrigger.build();

        //return the current
        return contractDTO;
    }

    public void deleteContract(String id){
        contractRepository.deleteById(id);

        logger.info("Adding audit trail for request.");
//        ContractAuditTrail contractAuditTrail = id.convertToAuditTrail();
//        contractAuditTrailRepository.save(contractAuditTrail);

        JenkinsTrigger.build();

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


    public List<ContractDTO> getContractDomainByConsumerInfo(String consumerSystem, String consumerName){
        if(StringUtils.isNotBlank(consumerName) && StringUtils.isNotBlank(consumerSystem)){
            return contractRepository.findByConsumerSystemAndConsumerID(consumerSystem, consumerName)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(consumerName)){
            return contractRepository.findByConsumerID(consumerName)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(consumerSystem)){
            return contractRepository.findByConsumerSystem(consumerSystem)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else{
            return contractRepository.findAll()
                    .stream().map(ContractDTO::new).collect(Collectors.toList());
        }

    }



}
