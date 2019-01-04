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
@Document(collection="contract")
public class Contract {

    @Id
    private String id;

    private String providerSystem;
    private String providerName;
    private String consumerSystem;
    private String consumerName;

    private String url;
    private String httpMethod;

    private String contract;
    private String contractType;


    public Contract(String providerSystem, String providerName, String consumerSystem, String consumerName) {
        this.providerSystem = providerSystem;
        this.providerName = providerName;
        this.consumerSystem = consumerSystem;
        this.consumerName = consumerName;
    }
}
