package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.repository.ContractRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    public List<ContractDTO> addOrUpdateContract(ContractDTO contractDTO){
        Contract contract = contractDTO.convertToEntity();
        contractRepository.save(contract);
        //query back the latest image from DB
        return contractRepository.findAll().stream().map(ContractDTO::new).collect(Collectors.toList());
    }

    public List<ContractDTO> getAllContract(){
        return contractRepository.findAll().stream().map(ContractDTO::new).collect(Collectors.toList());
    }

    public List<ContractDTO> deleteContract(ContractDTO contractDTO){
        Contract contract = contractDTO.convertToEntity();
        contractRepository.delete(contract);
        return contractRepository.findAll().stream().map(ContractDTO::new).collect(Collectors.toList());
    }


    public ContractDTO getContractDomainById(String id){
        return contractRepository.findById(id).map(contract -> new ContractDTO(contract)).orElse(null);
    }

    public List<ContractDTO> getContractDomainByUrl(String url){
        return contractRepository.findByUrl(url)
                .stream().map(ContractDTO::new).collect(Collectors.toList());
    }

    public List<ContractDTO> getContractDomainByProviderInfo(String providerSystem, String providerName){

        if(StringUtils.isNotBlank(providerName) && StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystemAndProviderName(providerSystem, providerName)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerName)){
            return contractRepository.findByProviderName(providerName)
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
            return contractRepository.findByConsumerSystemAndConsumerName(consumerSystem, consumerName)
                    .stream().map(ContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(consumerName)){
            return contractRepository.findByConsumerName(consumerName)
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
