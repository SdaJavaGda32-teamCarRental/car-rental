package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.Logotype;
import pl.sdacademy.carrental.repositories.LogotypeRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyService {
    private final BranchService branchService;
    private final LogotypeRepository logotypeRepository;


    public CompanyService(final BranchService branchService, final LogotypeRepository logotypeRepository) {
        this.branchService = branchService;
        this.logotypeRepository = logotypeRepository;
    }

    public List<Branch> getAllBranches() {
        return branchService.getAll();
    }

    public void saveImage(final MultipartFile imageFile) throws IOException {
        final Logotype logotype = Logotype.builder()
                .image(imageFile.getBytes())
                .build();

        logotypeRepository.save(logotype);
    }

    public List<Long> getAllLogotypeIds() {
        return logotypeRepository.findAll().stream()
                .map(Logotype::getId)
                .collect(Collectors.toList());
    }
}
