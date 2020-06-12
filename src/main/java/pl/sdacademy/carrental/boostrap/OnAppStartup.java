package pl.sdacademy.carrental.boostrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.BranchStatus;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.FuelType;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.BranchRepository;
import pl.sdacademy.carrental.repositories.CarRepository;

import java.util.List;


@Component
@Transactional
public class OnAppStartup implements ApplicationListener<ContextRefreshedEvent> {

    private final CarRepository carRepo;
    private final BranchRepository branchRepo;
    private final AddressRepository addressRepo;

    public OnAppStartup(final CarRepository carRepo,
                        final BranchRepository branchRepo,
                        final AddressRepository addressRepo) {
        this.carRepo = carRepo;
        this.branchRepo = branchRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        final Address gdAddress = addressRepo.save(Address.builder()
                .street("Grunwaldzka")
                .building("12D")
                .apartment("33")
                .zip("80-053")
                .city("Gdańsk")
                .build());

        final Branch gdansk = branchRepo.save(Branch.builder()
                .address(gdAddress)
                .city("Gdańsk")
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

        carRepo.saveAll(List.of(
                skoda,
                merc));
    }
}
