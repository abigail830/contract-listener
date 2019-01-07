package com.github.abigail830.contractlistener.service;

import com.github.abigail830.contractlistener.domain.StreamContractDTO;
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

    public List<StreamContractDTO> getStreamContractByProviderInfo(String providerSystem, String providerID){

        if(StringUtils.isNotBlank(providerID) && StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystemAndProviderID(providerSystem, providerID)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerID)){
            return contractRepository.findByProviderID(providerID)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else if(StringUtils.isNotBlank(providerSystem)){
            return contractRepository.findByProviderSystem(providerSystem)
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());

        }else{
            return contractRepository.findAll()
                    .stream().map(StreamContractDTO::new).collect(Collectors.toList());
        }
    }

}
