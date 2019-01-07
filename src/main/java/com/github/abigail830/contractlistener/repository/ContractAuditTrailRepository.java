package com.github.abigail830.contractlistener.repository;

import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractAuditTrailRepository extends MongoRepository<ContractAuditTrail, String> {

    public List<ContractAuditTrail> findByApi(String api);
//    public List<ContractAuditTrail> findByProviderSystemAndProviderName(String providerSystem, String providerID);
//    public List<ContractAuditTrail> findByConsumerSystemAndConsumerName(String consumerSystem, String consumerID);
}
