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
        this.setFileContent(contract.getRequest() + "\n" + contract.getResponse());
        this.setFileExtension(contract.getContractType());
        this.setFileName(contract.getId());

        if (contract.getConsumerName() != null && contract.getConsumerSystem() != null)
            this.setFilePath("contractFromConsumer" + "_" + contract.getConsumerSystem() + "." + contract.getConsumerName());
        else
            this.setFilePath("contractFromConsumer" + "_" + contract.getConsumerID());
    }

}
