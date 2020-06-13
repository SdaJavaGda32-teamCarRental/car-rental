package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.Status;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
   
   Optional<Car> findCarByPlateNumber(String plateNumber);
   
   List<Car> findCarsByCurrentStatusEquals(Status status);
   
   
}
