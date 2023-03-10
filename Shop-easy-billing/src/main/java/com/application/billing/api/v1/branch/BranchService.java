package com.application.billing.api.v1.branch;

import com.application.billing.api.v1.branch.pojo.CreateBranchBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;

    public Branch createBranch(CreateBranchBody createBranchBody) {
        Branch branch = new Branch();
        branch.setId(UUID.randomUUID().toString());
        branch.setName(createBranchBody.getName());
        branch.setShortName(createBranchBody.getShortName());
        branch.setCompanyId(createBranchBody.getCompanyId());
        branch.setCreatedOn(LocalDateTime.now());
        Branch save = branchRepository.save(branch);
        return save;
    }

    public List<Branch> getBranchesByCompanyId(String companyId){
        return branchRepository.findAllByCompanyId(companyId);
    }

}
