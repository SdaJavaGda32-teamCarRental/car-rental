package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.Company;
import pl.sdacademy.carrental.domain.Logotype;
import pl.sdacademy.carrental.model.CompanyForm;
import pl.sdacademy.carrental.repositories.CompanyRepository;
import pl.sdacademy.carrental.repositories.LogotypeRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyService {
    private static final Long COMPANY_ID = 1L;
    public static final int LOGOTYPE_WIDTH = 250;
    public static final int LOGOTYPE_HEIGHT = 100;
    private final BranchService branchService;
    private final LogotypeRepository logotypeRepository;
    private final CompanyRepository companyRepository;


    public CompanyService(final BranchService branchService, final LogotypeRepository logotypeRepository, final CompanyRepository companyRepository) {
        this.branchService = branchService;
        this.logotypeRepository = logotypeRepository;
        this.companyRepository = companyRepository;
    }

    public List<Branch> getAllBranches() {
        return branchService.getAll();
    }

    public void saveImage(final MultipartFile imageFile) throws IOException {
        //TODO Obsługa Nullpointera
        final Logotype logotype = Logotype.builder()
                .image(resizeImage(imageFile))
                .build();
        logotypeRepository.save(logotype);
    }

    private byte[] resizeImage(final MultipartFile imageFile) throws IOException {
        final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageFile.getBytes()));

        final Image tempImage = image.getScaledInstance(LOGOTYPE_WIDTH, LOGOTYPE_HEIGHT, Image.SCALE_SMOOTH);
        final BufferedImage scaledImage = new BufferedImage(LOGOTYPE_WIDTH, LOGOTYPE_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(tempImage, 0, 0, null);
        graphics2D.dispose();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, "png", bos);
        return bos.toByteArray();
    }

    public List<Long> getAllLogotypeIds() {
        return logotypeRepository.findAll().stream()
                .map(Logotype::getId)
                .collect(Collectors.toList());
    }

    public Company get() {
        return companyRepository.getOne(COMPANY_ID);
    }

    public void update(final Company company) {

    }

    public CompanyForm getCompanyForm() {
        final Company company = companyRepository.getOne(COMPANY_ID);
        return CompanyForm.builder()
                .name(company.getName())
                .domain(company.getDomain())
                .city(company.getAddress().getCity())
                .zipCode(company.getAddress().getZipCode())
                .street(company.getAddress().getStreet())
                .building(company.getAddress().getBuilding())
                .apartment(company.getAddress().getApartment())
                .logotypeId(company.getLogotype().getId())
                .build();
    }
}
