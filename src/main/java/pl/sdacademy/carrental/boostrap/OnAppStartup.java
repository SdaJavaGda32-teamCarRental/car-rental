package pl.sdacademy.carrental.boostrap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.*;
import pl.sdacademy.carrental.domain.cars.*;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;
import pl.sdacademy.carrental.repositories.CarRepository;
import pl.sdacademy.carrental.repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
                       final AddressRepository addressRepo,
                       final EmployeeRepository employeeRepo) {
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
   
      final Address ldzAddress = addressRepo.save(Address.builder()
            .street("Wigury")
            .building("21")
            .zipCode("90-435")
            .city("Łódź")
            .build());
   
      final Address wawAddress = addressRepo.save(Address.builder()
            .street("Puławska")
            .building("326")
            .zipCode("02-112")
            .city("Warszawa")
            .build());
   
      final Address krkAddress = addressRepo.save(Address.builder()
            .street("Zakopiańska")
            .building("58B")
            .zipCode("34-220")
            .city("Kraków")
            .build());
   
      final List<Branch> branches = new ArrayList<>();
   
      final Branch gdansk = (Branch.builder()
            .address(gdAddress)
            .name("Gdańsk")
            .status(BranchStatus.OPEN)
            .build());
      branches.add(gdansk);
   
      final Branch lodz = Branch.builder()
            .address(ldzAddress)
            .name("Łódź")
            .status(BranchStatus.OPEN)
            .build();
      branches.add(lodz);
      
      final Branch wawa = Branch.builder()
            .address(wawAddress)
            .name("Warszawa")
            .status(BranchStatus.OPEN)
            .build();
      branches.add(wawa);
      
      final Branch krakow = Branch.builder()
            .address(krkAddress)
            .name("Kraków")
            .status(BranchStatus.OPEN)
            .build();
      branches.add(krakow);
      
      branchRepo.saveAll(branches);
      
   
      final Car skoda = Car.builder()
            .make("Skoda")
            .model("Rapid")
            .fuelType(FuelType.GASOLINE)
            .category(CarCategory.B_PLUS)
            .productionYear(2019)
            .rentPrice(99)
            .currentStatus(Status.IN)
            .build();

      final Car merc = Car.builder()
            .make("Mercedes-Benz")
            .category(CarCategory.E)
            .model("E-Class")
            .fuelType(FuelType.DIESEL)
            .productionYear(2020)
            .rentPrice(320)
            .currentStatus(Status.IN)
            .build();

      final Car audi = Car.builder()
            .make("Audi")
            .model("A6")
            .category(CarCategory.D_PREMIUM)
            .fuelType(FuelType.DIESEL)
            .productionYear(2010)
            .rentPrice(250)
            .currentStatus(Status.IN)
            .build();
      
      final Car panda = Car.builder()
            .make("Fiat")
            .model("Panda")
            .category(CarCategory.A)
            .fuelType(FuelType.GASOLINE)
            .productionYear(2018)
            .rentPrice(50)
            .currentStatus(Status.IN)
            .build();
      
      final Car corsa = Car.builder()
            .make("Opel")
            .model("Corsa")
            .category(CarCategory.B)
            .fuelType(FuelType.GASOLINE)
            .productionYear(2020)
            .rentPrice(120)
            .currentStatus(Status.IN)
            .build();
   
      final List<Car> carTemplates = List.of(skoda, merc, audi, panda, corsa);
   
      List<Car> carsToSave = new ArrayList<>();
      
   
      for (Car template : carTemplates) {
         for (Branch branch : branches) {
            for (int j = 0; j < 50; j++) {
               carsToSave.add(
                     Car.builder()
                           .make(template.getMake())
                           .model(template.getModel())
                           .fuelType(template.getFuelType())
                           .productionYear(template.getProductionYear())
                           .rentPrice(template.getRentPrice())
                           .category(template.getCategory())
                           .currentStatus(template.getCurrentStatus())
                           .mileage(new Random().nextInt(20000))
                           .currentBranch(branch)
                           .plateNumber("GD"
                                 + RandomStringUtils.randomNumeric(3)
                                 + RandomStringUtils.randomAlphanumeric(2).toUpperCase())
                           .color(Color.values()[new Random().nextInt(Color.values().length)])
                           .isAutomatic(new Random().nextBoolean())
                           .build()
               );
            }
         }
      }
   
      carRepo.saveAll(carsToSave);
   
      final Employee marek = Employee.builder()
            .firstName("Marek")
            .lastName("Kowalski")
            .role(EmployeeRole.EMPLOYEE)
            .branch(krakow)
            .build();

      final Employee zenek = Employee.builder()
            .firstName("Zenek")
            .lastName("Poranek")
            .role(EmployeeRole.EMPLOYEE)
            .branch(gdansk)
            .build();

      employeeRepo.saveAll(List.of(
            marek,
            zenek));
   }
}
