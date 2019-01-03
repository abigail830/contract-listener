package com.github.abigail830.contractlistener.domain;

import com.github.abigail830.contractlistener.entity.Contract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ContractDTO")
public class ContractDTO {

    private String id;
    @ApiModelProperty(value = "契约生产者服务所属系统名称", example = "provider-system")
    private String providerSystem;

    @ApiModelProperty(value = "契约生产者服务名称", example = "provider")
    private String providerName;

    @ApiModelProperty(value = "契约消费者服务所属系统名称",example = "consumer-system")
    private String consumerSystem;

    @ApiModelProperty(value = "契约消费者服务名称",example = "consumer")
    private String consumerName;

    @ApiModelProperty(value = "契约针对的HTTP路径", example = "/info/name")
    private String url;
    @ApiModelProperty(value = "契约针对的HTTP方式", example = "GET")
    private String httpMethod;
    @ApiModelProperty(value = "契约内容")
    private String contract;
    @ApiModelProperty(value = "契约类型", notes = "如yml或者groovy", example = "yml")
    private String contractType;

    public ContractDTO(Contract contractEntity) {
        this.setId(contractEntity.getId());
        this.setProviderName(contractEntity.getProviderName());
        this.setProviderSystem(contractEntity.getProviderSystem());
        this.setConsumerName(contractEntity.getConsumerName());
        this.setConsumerSystem(contractEntity.getConsumerSystem());
        this.setUrl(contractEntity.getUrl());
        this.setHttpMethod(contractEntity.getHttpMethod());
        this.setContract(contractEntity.getContract());
        this.setContractType(contractEntity.getContractType());
    }

    public Contract convertToEntity() {
        Contract contract =new Contract();
        contract.setId(this.getId());
        contract.setProviderName(this.getProviderName());
        contract.setProviderSystem(this.getProviderSystem());
        contract.setConsumerName(this.getConsumerName());
        contract.setConsumerSystem(this.getConsumerSystem());
        contract.setUrl(this.getUrl());
        contract.setHttpMethod(this.getHttpMethod());
        contract.setContract(this.getContract());
        contract.setContractType(this.getContractType());
        return contract;
    }
}
