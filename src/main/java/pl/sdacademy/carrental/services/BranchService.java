package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.repositories.BranchRepository;
import pl.sdacademy.carrental.repositories.CarRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
public class BranchService {
    public final static int MINIMUM_CAR_COUNT = 3;
    
    private final BranchRepository branchRepo;
    
    public BranchService(BranchRepository branchRepo) {
        this.branchRepo = branchRepo;
    }

    public List<Branch> getAll() {
        return branchRepo.findAll();
    }
    
    public Map<Branch, Integer> getBranchesWithCarCount() {
        return getAll().stream().collect(Collectors.toMap(branch -> branch, branch -> branch.getCarsOnHand().size()));
    }

}
