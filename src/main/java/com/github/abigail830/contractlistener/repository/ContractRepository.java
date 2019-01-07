package com.github.abigail830.contractlistener.repository;

import com.github.abigail830.contractlistener.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {

    public List<Contract> findByApi(String api);
    public List<Contract> findByProviderSystemAndProviderID(String providerSystem, String providerID);
    public List<Contract> findByProviderSystem(String providerSystem);
    public List<Contract> findByProviderID(String providerID);
    public List<Contract> findByConsumerSystemAndConsumerID(String consumerSystem, String consumerID);
    public List<Contract> findByConsumerSystem(String consumerSystem);
    public List<Contract> findByConsumerID(String consumerID);
//
//    public List<Contract> findByConsumerSystemAndConsumerIDAndProviderSystemAndProviderIDAndApiAndMethod(
//            String consumerSystem, String consumerID,
//            String providerSystem, String providerID,
//            String api, String method);
}
