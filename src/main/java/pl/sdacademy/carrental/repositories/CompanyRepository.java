package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
