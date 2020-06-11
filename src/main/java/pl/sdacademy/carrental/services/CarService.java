package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.repositories.CarRepository;

import java.util.*;
import java.util.stream.IntStream;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepo;
    private final static int MINIMUM_CAR_COUNT = 3;
    private final static int NO_AVAILABLE_CARS = 0;

    public CarService(final CarRepository carRepo) {
        this.carRepo = carRepo;
    }


    public List<Car> getAll() {
        return carRepo.findAll();
    }

    public List<Car> getAvailable() {
        return carRepo.findCarsByCurrentStatusEquals(Status.IN);
    }

    public long countAvailableCars() {
        return getAvailable().stream().count();
    }

    public boolean shouldNotifyAboutLowCarAvailability() {
        long carCounter = countAvailableCars();
       return carCounter < MINIMUM_CAR_COUNT && carCounter > NO_AVAILABLE_CARS;
    }
}
