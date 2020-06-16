package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.BranchStatus;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findBranchByAddress(final Address address);

    List<Branch> getAllByStatus(final BranchStatus status);
   
   Optional<Branch> findBranchByName(String branchName);
}
