package pl.sdacademy.carrental.boostrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.*;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.FuelType;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;
import pl.sdacademy.carrental.repositories.CarRepository;
import pl.sdacademy.carrental.repositories.EmployeeRepository;

import java.util.List;


@Profile("dev")
@Component
@Transactional
public class OnAppStartup implements ApplicationListener<ContextRefreshedEvent> {
   
   private final CarRepository carRepo;
   private final BranchRepository branchRepo;
   private final AddressRepository addressRepo;
   private final EmployeeRepository employeeRepo;
   
   public OnAppStartup(final CarRepository carRepo,
                       final BranchRepository branchRepo,
                       final AddressRepository addressRepo, EmployeeRepository employeeRepo) {
      this.carRepo = carRepo;
      this.branchRepo = branchRepo;
      this.addressRepo = addressRepo;
      this.employeeRepo = employeeRepo;
   }
   
   @Override
   public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
      
      final Address gdAddress = addressRepo.save(Address.builder()
            .street("Grunwaldzka")
            .building("12D")
            .zipCode("80-053")
            .city("Gdańsk")
            .build());

      final Address gdAddress2 = addressRepo.save(Address.builder()
            .street("Mickiewicza")
            .building("5F")
            .zipCode("80-435")
            .city("Gdańsk")
            .build());
      
      final Branch gdansk = branchRepo.save(Branch.builder()
            .address(gdAddress)
            .name("Gdańsk")
            .status(BranchStatus.OPEN)
            .build());

      final Branch gdansk2 = branchRepo.save(Branch.builder()
            .address(gdAddress2)
            .name("Gdańsk")
            .status(BranchStatus.OPEN)
            .build());
      
      final Car skoda = Car.builder()
            .make("Skoda")
            .model("Rapid")
            .fuelType(FuelType.GASOLINE)
            .productionYear(2019)
            .plateNumber("GD312KF")
            .color("white")
            .rentPrice(99)
            .currentStatus(Status.IN)
            .currentBranch(gdansk)
            .build();

      final Car merc = Car.builder()
            .make("Mercedes-Benz")
            .model("E-Class")
            .fuelType(FuelType.DIESEL)
            .productionYear(2020)
            .plateNumber("GD123XW")
            .color("black")
            .rentPrice(320)
            .currentStatus(Status.OUT)
            .currentBranch(gdansk)
            .build();

      final Car audi = Car.builder()
            .make("Audi")
            .model("A6")
            .fuelType(FuelType.DIESEL)
            .productionYear(2010)
            .plateNumber("GD246DW")
            .color("red")
            .rentPrice(250)
            .currentStatus(Status.OUT)
            .currentBranch(gdansk2)
            .build();

      final Car panda = Car.builder()
            .make("FIAT")
            .model("Panda")
            .fuelType(FuelType.GASOLINE)
            .productionYear(2018)
            .plateNumber("GD223KS")
            .color("pink")
            .rentPrice(50)
            .currentStatus(Status.IN)
            .currentBranch(gdansk)
            .build();
      
      final Car corsa = Car.builder()
            .make("Opel")
            .model("Corsa")
            .fuelType(FuelType.GASOLINE)
            .productionYear(2020)
            .plateNumber("GD6232T")
            .color("Yellow")
            .rentPrice(120)
            .currentStatus(Status.IN)
            .currentBranch(gdansk)
            .build();
      
      final Employee marek = Employee.builder()
            .firstName("Marek")
            .lastName("Kowalski")
            .role(EmployeeRole.EMPLOYEE)
            .branch(gdansk2)
            .build();

      final Employee zenek = Employee.builder()
            .firstName("Zenek")
            .lastName("Poranek")
            .role(EmployeeRole.EMPLOYEE)
            .branch(gdansk)
            .build();

      carRepo.saveAll(List.of(
            skoda,
            merc,
            audi,
            panda,
            corsa));

      employeeRepo.saveAll(List.of(
            marek,
            zenek));
   }
}
