package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.Status;
import pl.sdacademy.carrental.requests.CarReservationRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationService {
   
   private final BranchService branchService;
   
   public ReservationService(final BranchService branchService) {
      this.branchService = branchService;
   }
   
   public List<Car> getAvailableCarsByReservationRequest(final CarReservationRequest request) {
      final Branch pickupBranch = branchService.findBranchByName(request.getPickupBranchName());
      final LocalDate pickupDate = LocalDate.parse(request.getPickupDate());
      final LocalDate returnDate = LocalDate.parse(request.getReturnDate());
      return pickupBranch.getCarsOnHand().stream()
            .filter(car->car.getCurrentStatus().equals(Status.IN)).collect(Collectors.toList());
   }
   
}
