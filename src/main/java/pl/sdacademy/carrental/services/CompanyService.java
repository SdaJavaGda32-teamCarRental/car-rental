package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.model.BranchForm;

import java.util.List;

@Service
@Transactional
public class CompanyService {
    private final BranchService branchService;


    public CompanyService(final BranchService branchService) {
        this.branchService = branchService;
    }

    public List<Branch> getAllBranches(){
        return branchService.getAll();
    }

}
