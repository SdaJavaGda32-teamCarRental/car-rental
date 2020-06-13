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

   public Car updateCarRentPrice(final Long id, final int price) {
      final Car carToUpdate = carRepo.getOne(id);
      carToUpdate.setRentPrice(price);
      return carRepo.save(carToUpdate);
   }

   public Car updateCarMileage(final Long id, final int mileage) {
      final Car carToUpdate = carRepo.getOne(id);
      if (carToUpdate.getMileage() > mileage) {
         // TODO: 11/06/2020 : Define, throw and handle a dedicated exception instead of a generic one
         throw new RuntimeException("Mileage cannot be lower than it was : " + carToUpdate.getMileage());
      }
      return carRepo.save(carToUpdate);
   }
}
