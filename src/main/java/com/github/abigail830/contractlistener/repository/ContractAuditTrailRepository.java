package com.github.abigail830.contractlistener.repository;

import com.github.abigail830.contractlistener.entity.ContractAuditTrail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ContractAuditTrailRepository extends MongoRepository<ContractAuditTrail, String>,
        QueryByExampleExecutor<ContractAuditTrail> {

}
