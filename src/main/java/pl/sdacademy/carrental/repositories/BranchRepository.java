package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
