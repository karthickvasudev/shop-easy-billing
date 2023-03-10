package com.application.billing.api.v1.branch;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
    List<Branch> findAllByCompanyId(String companyId);
}
