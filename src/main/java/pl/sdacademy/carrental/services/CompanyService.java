package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.carrental.domain.Branch;

import java.util.List;

@Service
@Transactional
public class CompanyService {
    private final BranchService branchService;


    public CompanyService(final BranchService branchService) {
        this.branchService = branchService;
    }

    public List<Branch> getAllBranches() {
        return branchService.getAll();
    }


    public void saveImage(final String filename, final MultipartFile imageFile){

    }
}
