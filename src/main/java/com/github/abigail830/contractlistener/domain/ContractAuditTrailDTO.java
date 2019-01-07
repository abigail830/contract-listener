package com.github.abigail830.contractlistener.domain;

import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.joda.time.DateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ContractDTO")
public class ContractAuditTrailDTO {

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

    @ApiModelProperty(value = "契约内容Request部分")
    private String request;

    @ApiModelProperty(value = "契约内容Response部分")
    private String response;

    @ApiModelProperty(value = "契约类型", notes = "如yml或者groovy", example = "yml")
    private String contractType;

    @ApiModelProperty(value = "最后修改契约的时间", example = "yml")
    private DateTime lastModified;

    @ApiModelProperty(value = "最后修改契约的用户", example = "SaraQian")
    private String lastModifiedBy;

    public ContractAuditTrailDTO(ContractAuditTrail contractAuditTrail) {
        this.setId(contractAuditTrail.getId());
        this.setProviderID(contractAuditTrail.getProviderID());
        this.setProviderSystem(contractAuditTrail.getProviderSystem());
        this.setConsumerID(contractAuditTrail.getConsumerID());
        this.setConsumerSystem(contractAuditTrail.getConsumerSystem());
        this.setApi(contractAuditTrail.getApi());
        this.setMethod(contractAuditTrail.getMethod());
        this.setRequest(contractAuditTrail.getRequest());
        this.setResponse(contractAuditTrail.getResponse());
        this.setContractType(contractAuditTrail.getContractType());
        this.setLastModified(contractAuditTrail.getLastModified());
        this.setLastModifiedBy(contractAuditTrail.getLastModifiedBy());
    }

//    public ContractAuditTrail convertToAuditTrail() {
//        ContractAuditTrail contractAuditTrail =new ContractAuditTrail();
//        contractAuditTrail.setId(this.getId());
//        contractAuditTrail.setProviderID(this.getProviderID());
//        contractAuditTrail.setProviderSystem(this.getProviderSystem());
//        contractAuditTrail.setConsumerID(this.getConsumerID());
//        contractAuditTrail.setConsumerSystem(this.getConsumerSystem());
//        contractAuditTrail.setApi(this.getApi());
//        contractAuditTrail.setMethod(this.getMethod());
//        contractAuditTrail.setRequest(this.getRequest());
//        contractAuditTrail.setResponse(this.getResponse());
//        contractAuditTrail.setContractType(this.getContractType());
//        return contractAuditTrail;
//    }
}
