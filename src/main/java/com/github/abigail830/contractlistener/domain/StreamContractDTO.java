package com.github.abigail830.contractlistener.domain;

import com.github.abigail830.contractlistener.entity.Contract;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StreamContractDTO {

    private String fileName;
    private String filePath;
    private String fileContent;
    private String fileExtension;

    public StreamContractDTO(Contract contract){
        this.setFileContent(contract.getRequest());
        this.setFileExtension(contract.getContractType());
        this.setFileName(contract.getId());
        this.setFilePath(contract.getProviderSystem()+"."+contract.getProviderID()+
                "_"+contract.getConsumerSystem()+"."+contract.getConsumerID());

    }

}
