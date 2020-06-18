package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.CarCategory;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;

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

        final Branch branch = Branch.builder()
                .name(branchForm.getName())
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

        final Address address = createAddressFromBranchForm(branchForm);

        final Branch branch = branchRepository.findById(id).orElseThrow();
        branch.setAddress(address);
        branch.setName(branchForm.getName());
        branch.setStatus(branchForm.getStatus());
        branchRepository.save(branch);
    }

    private Address createAddressFromBranchForm(final BranchForm branchForm){
        final Address address = Address.builder()
                .city(branchForm.getCity())
                .street(branchForm.getStreet())
                .building(branchForm.getBuilding())
                .apartment(branchForm.getApartment())
                .zipCode(branchForm.getZipCode())
                .build();
        return addressRepository.save(address);
    }

    public BranchForm getById(final Long id) {
        final Branch branch = branchRepository.findById(id).orElseThrow();
        final Address address = branch.getAddress();
        return BranchForm.builder()
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
         // TODO: 16/06/2020 throw and handle a dedicated exception
         throw new RuntimeException("Branch of name " + branchName + " not found!");
      });
   }
}
