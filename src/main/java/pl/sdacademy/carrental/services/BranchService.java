package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.repositories.BranchRepository;

import java.util.List;


@Service
@Transactional
public class BranchService {

    private final BranchRepository branchRepo;

    public BranchService(BranchRepository branchRepo) {
        this.branchRepo = branchRepo;
    }

    public List<Branch> getAll() {
        return branchRepo.findAll();
    }

}
