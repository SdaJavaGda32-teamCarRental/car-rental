package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.CarCategory;
import pl.sdacademy.carrental.domain.cars.Status;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
   
   Optional<Car> findCarByPlateNumber(String plateNumber);
   
   List<Car> findCarsByCurrentStatusEquals(Status status);
   
   int countCarsByCategory(CarCategory category);
   
   Optional<Car> findFirstByCategory(CarCategory category);
   
   List<Car> findCarsByCategoryAndCurrentStatusAndCurrentBranch(CarCategory category, Status currentStatus, Branch currentBranch);
   
   int countCarsByCategoryAndCurrentStatusAndCurrentBranch(CarCategory category, Status currentStatus, Branch currentBranch);
   
}
