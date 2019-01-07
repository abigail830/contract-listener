package com.github.abigail830.contractlistener.entity;

import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;
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

    private String providerSystem;
    private String providerID;
    private String consumerSystem;
    private String consumerID;

    private String api;
    private String method;

    private String request;
    private String response;

    private String contractType;

//    @CreatedDate
//    private DateTime createdAt;

//    @CreatedBy
//    private String createdBy;

    @LastModifiedDate
    private DateTime lastModified;

    @LastModifiedBy
    private String lastModifiedBy;

}


