package com.github.abigail830.contractlistener.repository;

import com.github.abigail830.contractlistener.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {

    public List<Contract> findByUrl(String url);
    public List<Contract> findByProviderSystemAndProviderName(String providerSystem, String providerName);
    public List<Contract> findByProviderSystem(String providerSystem);
    public List<Contract> findByProviderName(String providerName);
    public List<Contract> findByConsumerSystemAndConsumerName(String consumerSystem, String consumerName);
    public List<Contract> findByConsumerSystem(String consumerSystem);
    public List<Contract> findByConsumerName(String consumerName);
}
