package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;

import java.util.List;

@Service
@Transactional
public class BranchService {
    private final BranchRepository branchRepository;
    private final AddressRepository addressRepository;

    public BranchService(final BranchRepository branchRepository,
                         final AddressRepository addressRepository) {
        this.branchRepository = branchRepository;
        this.addressRepository = addressRepository;
    }

    public List<Branch> getAll() {
        return branchRepository.findAll();
    }


    public void createBranch(final BranchForm branchForm) {
        final Address address = Address.builder()
                .city(branchForm.getCity())
                .street(branchForm.getStreet())
                .building(branchForm.getBuilding())
                .apartment(branchForm.getApartment())
                .zip(branchForm.getZip())
                .build();
        addressRepository.save(address);

        final Branch branch = Branch.builder()
                .city(branchForm.getCity())
                .address(address)
                .status(branchForm.getStatus())
                .build();
        branchRepository.save(branch);
    }

    public void delete(final Long id) {
        final Branch branch = branchRepository.findById(id).orElseThrow();
        branchRepository.delete(branch);
    }

    public void updateBranch(final Long id, final BranchForm branchForm) {
        final Address address = Address.builder()
                .city(branchForm.getCity())
                .street(branchForm.getStreet())
                .building(branchForm.getBuilding())
                .apartment(branchForm.getApartment())
                .zip(branchForm.getZip())
                .build();
        addressRepository.save(address);

        final Branch branch = getById(id);
        branch.setAddress(address);
        branch.setCity(address.getCity());
        branch.setStatus(branchForm.getStatus());
        branchRepository.save(branch);
    }

    public Branch getById(final Long id) {
        return branchRepository.findById(id).orElseThrow();
    }
}
