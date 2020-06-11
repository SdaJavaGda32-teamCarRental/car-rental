package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {
   
   private final CarRepository carRepo;
   
   public CarService(final CarRepository carRepo) {
      this.carRepo = carRepo;
   }
   
   
   public List<Car> getAll() {
      return carRepo.findAll();
   }
   
   public List<Car> getAvailable() {
      return carRepo.findCarsByCurrentStatusEquals(Status.IN);
   }

   public long countAvailableCars(){
      return getAvailable().size();
   }

   public boolean availableOnlyOneOrTwoCars(){
      return countAvailableCars() == 1 || countAvailableCars() == 2;
   }
}
