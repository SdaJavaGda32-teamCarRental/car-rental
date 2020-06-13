package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;
import pl.sdacademy.carrental.repositories.CarRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Transactional
public class BranchService {
    public final static int MINIMUM_CAR_COUNT = 3;
  
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
                .zipCode(branchForm.getZipCode())
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
                .zipCode(branchForm.getZipCode())
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
    
    public Map<Branch, Long> getBranchesWithCarCount() {
        return getAll().stream()
              .collect(Collectors.toMap(
                    Function.identity(),
                    this::getAvailableCarCount));
    }
    
    private long getAvailableCarCount(final Branch branch) {
        return branch.getCarsOnHand().stream()
              .filter(car-> car.getCurrentStatus()
                    .equals(Status.IN)).count();
    }
    
}
