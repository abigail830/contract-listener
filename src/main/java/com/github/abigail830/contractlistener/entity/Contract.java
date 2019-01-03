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
@Document
public class Contract {

    @Id
    public String id;

    public String providerSystem;
    public String providerName;
    public String consumerSystem;
    public String consumerName;

    public String url;
    public String httpMethod;

    public String contract;
    public String contractType;


    public Contract(String providerSystem, String providerName, String consumerSystem, String consumerName) {
        this.providerSystem = providerSystem;
        this.providerName = providerName;
        this.consumerSystem = consumerSystem;
        this.consumerName = consumerName;
    }
}
