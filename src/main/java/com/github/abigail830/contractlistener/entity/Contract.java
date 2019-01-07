package com.github.abigail830.contractlistener.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(collection="request")
public class Contract {

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
    private String desc;
    private String contractType;


}
