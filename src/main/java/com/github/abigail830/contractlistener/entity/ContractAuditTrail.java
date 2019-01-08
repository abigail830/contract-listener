package com.github.abigail830.contractlistener.entity;

import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="audit_trail")
public class ContractAuditTrail {

    @Id
    private String id;

    private String contractID;
    private String providerSystem;
    private String providerID;
    private String providerName;
    private String consumerSystem;
    private String consumerID;
    private String consumerName;
    private String api;
    private String method;
    private String request;
    private String response;
    private String desc;
    private String contractType;

    private String lastModifiedAction;

//    @CreatedDate
//    private DateTime createdAt;
//
//    @CreatedBy
//    private String createdBy;

    @LastModifiedDate
    private DateTime lastModified;

    @LastModifiedBy
    private String lastModifiedBy;

}


