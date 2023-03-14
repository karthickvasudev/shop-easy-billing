package com.application.billing.api.v1.branch;

import com.application.billing.api.v1.branch.pojo.CreateBranchBody;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/branch")
public class BranchController {
    private final BranchService service;

    @PostMapping("create")
    public Branch createBranch(@RequestBody CreateBranchBody createBranchBody) {
        return service.createBranch(createBranchBody);
    }

        @GetMapping("{companyId}")
        public List<Branch> getBranchByCompanyId(@PathVariable("companyId") String companyId){
            return service.getBranchesByCompanyId(companyId);

    }
}
