package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Employee;
import pl.sdacademy.carrental.domain.EmployeeRole;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {


//    List<Employee> findEmployeesByRole(EmployeeRole role);

}
