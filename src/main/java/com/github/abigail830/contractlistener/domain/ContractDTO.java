package com.github.abigail830.contractlistener.domain;

import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
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
    private String providerID;

    @ApiModelProperty(value = "契约消费者服务所属系统名称",example = "consumer-system")
    private String consumerSystem;

    @ApiModelProperty(value = "契约消费者服务名称",example = "consumer")
    private String consumerID;

    @ApiModelProperty(value = "契约针对的HTTP路径", example = "/info/name")
    private String api;
    @ApiModelProperty(value = "契约针对的HTTP方式", example = "GET")
    private String method;

    @ApiModelProperty(value = "契约内容Request部分", example = "Contract Request content")
    private String request;

    @ApiModelProperty(value = "契约内容Response部分", example="Contract Response content")
    private String response;

    @ApiModelProperty(value = "契约概要描述", example="This is the description")
    private String desc;

    @ApiModelProperty(value = "契约类型", notes = "如yml或者groovy", example = "yml")
    private String contractType;

    public ContractDTO(Contract contractEntity) {
        this.setId(contractEntity.getId());
        this.setProviderID(contractEntity.getProviderID());
        this.setProviderSystem(contractEntity.getProviderSystem());
        this.setConsumerID(contractEntity.getConsumerID());
        this.setConsumerSystem(contractEntity.getConsumerSystem());
        this.setApi(contractEntity.getApi());
        this.setMethod(contractEntity.getMethod());
        this.setRequest(contractEntity.getRequest());
        this.setResponse(contractEntity.getResponse());
        this.setDesc(contractEntity.getDesc());
        this.setContractType(contractEntity.getContractType());
    }

    public Contract convertToEntity() {
        Contract contract =new Contract();
        contract.setId(this.getId());
        contract.setProviderID(this.getProviderID());
        contract.setProviderSystem(this.getProviderSystem());
        contract.setConsumerID(this.getConsumerID());
        contract.setConsumerSystem(this.getConsumerSystem());
        contract.setApi(this.getApi());
        contract.setMethod(this.getMethod());
        contract.setRequest(this.getRequest());
        contract.setResponse(this.getResponse());
        contract.setDesc(this.getDesc());
        contract.setContractType(this.getContractType());
        return contract;
    }

    public ContractAuditTrail convertToAuditTrail() {
        ContractAuditTrail contractAuditTrail = new ContractAuditTrail();
        contractAuditTrail.setContractID(this.getId());
        contractAuditTrail.setProviderID(this.getProviderID());
        contractAuditTrail.setProviderSystem(this.getProviderSystem());
        contractAuditTrail.setConsumerID(this.getConsumerID());
        contractAuditTrail.setConsumerSystem(this.getConsumerSystem());
        contractAuditTrail.setApi(this.getApi());
        contractAuditTrail.setMethod(this.getMethod());
        contractAuditTrail.setRequest(this.getRequest());
        contractAuditTrail.setResponse(this.getResponse());
        contractAuditTrail.setDesc(this.getDesc());
        contractAuditTrail.setContractType(this.getContractType());
        return contractAuditTrail;
    }
}
