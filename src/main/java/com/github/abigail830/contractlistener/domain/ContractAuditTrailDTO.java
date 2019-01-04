package com.github.abigail830.contractlistener.domain;

import com.github.abigail830.contractlistener.entity.Contract;
import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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

    @ApiModelProperty(value = "最后修改契约的时间", example = "yml")
    private DateTime lastModified;

    @ApiModelProperty(value = "最后修改契约的用户", example = "SaraQian")
    private String lastModifiedBy;

    public ContractAuditTrailDTO(ContractAuditTrail contractAuditTrail) {
        this.setId(contractAuditTrail.getId());
        this.setProviderName(contractAuditTrail.getProviderName());
        this.setProviderSystem(contractAuditTrail.getProviderSystem());
        this.setConsumerName(contractAuditTrail.getConsumerName());
        this.setConsumerSystem(contractAuditTrail.getConsumerSystem());
        this.setUrl(contractAuditTrail.getUrl());
        this.setHttpMethod(contractAuditTrail.getHttpMethod());
        this.setContract(contractAuditTrail.getContract());
        this.setContractType(contractAuditTrail.getContractType());
        this.setLastModified(contractAuditTrail.getLastModified());
        this.setLastModifiedBy(contractAuditTrail.getLastModifiedBy());
    }

//    public ContractAuditTrail convertToAuditTrail() {
//        ContractAuditTrail contractAuditTrail =new ContractAuditTrail();
//        contractAuditTrail.setId(this.getId());
//        contractAuditTrail.setProviderName(this.getProviderName());
//        contractAuditTrail.setProviderSystem(this.getProviderSystem());
//        contractAuditTrail.setConsumerName(this.getConsumerName());
//        contractAuditTrail.setConsumerSystem(this.getConsumerSystem());
//        contractAuditTrail.setUrl(this.getUrl());
//        contractAuditTrail.setHttpMethod(this.getHttpMethod());
//        contractAuditTrail.setContract(this.getContract());
//        contractAuditTrail.setContractType(this.getContractType());
//        return contractAuditTrail;
//    }
}
