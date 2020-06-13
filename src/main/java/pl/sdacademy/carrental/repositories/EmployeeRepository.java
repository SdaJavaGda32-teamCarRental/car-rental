package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
