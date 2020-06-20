package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.Company;
import pl.sdacademy.carrental.domain.Logotype;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.model.CompanyForm;
import pl.sdacademy.carrental.repositories.AddressRepository;
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
    private final AddressRepository addressRepository;


    public CompanyService(final BranchService branchService, final LogotypeRepository logotypeRepository, final CompanyRepository companyRepository, final AddressRepository addressRepository) {
        this.branchService = branchService;
        this.logotypeRepository = logotypeRepository;
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
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

    public void update(final CompanyForm companyForm) {
        final Company company = get();

        final Address address = createAddressFromForm(companyForm);
        final Address originalAddress = company.getAddress();

        //TODO refactor - wydzielenie osobnej metody
        if (!address.equals(originalAddress)) {
            addressRepository.save(address);
            company.setAddress(address);
            addressRepository.delete(originalAddress);
        }

        company.setLogotype(logotypeRepository.getOne(companyForm.getLogotypeId()));
        company.setName(companyForm.getName());
        company.setDomain(companyForm.getDomain());
        companyRepository.save(company);
    }

    //TODO - refactor na generic! razem z createAddressFromBranchForm - wszystkie Formy implementują np. GenericForm?
    private Address createAddressFromForm(final CompanyForm companyForm) {
        return Address.builder()
                .city(companyForm.getCity())
                .street(companyForm.getStreet())
                .building(companyForm.getBuilding())
                .apartment(companyForm.getApartment())
                .zipCode(companyForm.getZipCode())
                .build();
    }

    public CompanyForm getCompanyForm() {
        final Company company = get();
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
