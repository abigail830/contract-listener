package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.domain.StreamContractDTO;
import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.repository.ContractRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreamContractService {

    @Autowired
    ContractRepository contractRepository;

    public List<StreamContractDTO> getAllStreamContract(){
        return contractRepository.findAll().stream().map(StreamContractDTO::new).collect(Collectors.toList());
    }

    public List<StreamContractDTO> getStreamContractByProviderInfo(String providerSystem, String providerName){

        if(StringUtils.isNotBlank(providerName) && StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystemAndProviderName(providerSystem, providerName)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerName)){
            return contractRepository.findByProviderName(providerName)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystem(providerSystem)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else{
            return contractRepository.findAll()
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());
        }
    }

    public List<StreamContractDTO> getStreamContractByConsumerInfo(String consumerSystem, String consumerName){

        if(StringUtils.isNotBlank(consumerName) && StringUtils.isNotBlank(consumerSystem)){
            return contractRepository.findByConsumerSystemAndConsumerName(consumerSystem, consumerName)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(consumerName)){
            return contractRepository.findByConsumerName(consumerName)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(consumerSystem)){
            return contractRepository.findByConsumerSystem(consumerSystem)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else{
            return contractRepository.findAll()
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());
        }

    }
}
