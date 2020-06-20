package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.CarCategory;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.exceptions.BranchException;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


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

        final Address address = createAddressFromBranchForm(branchForm);
        throwIfBranchWithGivenAddressExists(address);

        addressRepository.save(address);

        final Branch branch = Branch.builder()
                .name(branchForm.getName())
                .address(address)
                .status(branchForm.getStatus())
                .build();
        branchRepository.save(branch);
    }

    public void delete(final Long id) {

        final Branch branch = findExistingById(id);
        final Address address = branch.getAddress();

        throwIfBranchHasCarsOrEmployees(branch);

        branchRepository.delete(branch);
        addressRepository.delete(address);
    }

    private Branch findExistingById(final Long id) {
        return branchRepository.findById(id).orElseThrow(() ->
                new BranchException("No branch with id " + id + " found."));
    }

    private void throwIfBranchHasCarsOrEmployees(final Branch branch) {
        boolean isDeletable = true;
        String expMessage = "";
        if (!branch.getCarsOnHand().isEmpty()) {
            isDeletable = false;
            expMessage += "Cannot remove a branch with assigned car(s). ";
        }
        if (!branch.getEmployees().isEmpty()) {
            isDeletable = false;
            expMessage += "Cannot remove a branch with assigned employee(s).";
        }
        if (!isDeletable) {
            throw new BranchException(expMessage);
        }
    }

    public void updateBranch(final Long id, final BranchForm branchForm) {

        final Branch branch = findExistingById(id);
        final Address originalAddress = branch.getAddress();

        final Address address = createAddressFromBranchForm(branchForm);

        if (!address.equals(originalAddress)) {
            addressRepository.save(address);
            branch.setAddress(address);
            addressRepository.delete(originalAddress);
        }

        branch.setName(branchForm.getName());
        branch.setStatus(branchForm.getStatus());
        branchRepository.save(branch);
    }

    private Address createAddressFromBranchForm(final BranchForm branchForm) {
        return Address.builder()
                .city(branchForm.getCity())
                .street(branchForm.getStreet())
                .building(branchForm.getBuilding())
                .apartment(branchForm.getApartment())
                .zipCode(branchForm.getZipCode())
                .build();
    }

    private void throwIfBranchWithGivenAddressExists(final Address address) {
        addressRepository.findExistingAddress(address.getCity(),
                address.getZipCode(),
                address.getStreet(),
                address.getBuilding(),
                address.getApartment()).stream()
                .findFirst().ifPresent(a -> {
            throw new BranchException("A branch with this address: " + address.toString() + " already exists.");
        });
    }

    public BranchForm getById(final Long id) {
        final Branch branch = findExistingById(id);
        final Address address = branch.getAddress();
        return BranchForm.builder()
              .name(branch.getName())
                .city(address.getCity())
                .street(address.getStreet())
                .building(address.getBuilding())
                .apartment(address.getApartment())
                .zipCode(address.getZipCode())
                .status(branch.getStatus())
                .build();

    }

    public Map<Branch, Map<CarCategory, Long>> getBranchesWithCarCount() {
        return getAll().stream()
              .collect(Collectors.toMap(
                    Function.identity(),
                    this::getAvailableCarCountPerCategory));

   }
   
   private Map<CarCategory, Long> getAvailableCarCountPerCategory(final Branch branch) {
       return branch.getCarsOnHand().stream()
             .filter(car -> car.getCurrentStatus() == Status.IN)
             .collect(Collectors.groupingBy(
                   Car::getCategory,
                   Collectors.counting()));
   }
   
   public Branch findBranchByName(final String branchName) {
      return branchRepository.findByName(branchName).orElseThrow(() -> {
         throw new BranchException("Branch of name " + branchName + " not found!");
      });
   }
}
