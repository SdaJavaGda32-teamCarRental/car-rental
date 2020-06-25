package pl.sdacademy.carrental.mappers;

import org.springframework.stereotype.Component;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.model.ReservationListDetails;
import pl.sdacademy.carrental.requests.CarReservationRequest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static pl.sdacademy.carrental.configuration.DomainValues.UPCHARGE_FOR_CHANGE_OF_BRANCH;

@Component
public class CarToReservationListItemDetailsMapper {
   
   public ReservationListDetails mapCarToListItem(final Car car, CarReservationRequest request) {
      final LocalDate pickupDate = request.getPickupDate();
      final LocalDate returnDate = request.getReturnDate();
      final int daysOfRent = (int) ChronoUnit.DAYS.between(pickupDate, returnDate.plusDays(1));
      int cost = getTotalCost(car, request, daysOfRent);
   
      return ReservationListDetails.builder()
            .makeAndModel(car.getMake() + " " + car.getModel())
            .fuelType(car.getFuelType())
            .pricePerDay(car.getRentPrice())
            .carCategory(car.getCategory())
            .isAutomatic(car.isAutomatic())
            .rentalCost(cost)
            .build();
   }
   
   private int getTotalCost(final Car car, final CarReservationRequest request, final int daysOfRent) {
      int cost = car.getRentPrice() * daysOfRent;
      if (!request.getPickupBranchName().equals(request.getReturnBranchName())) {
         cost += UPCHARGE_FOR_CHANGE_OF_BRANCH;
      }
      return cost;
   }
   
}
