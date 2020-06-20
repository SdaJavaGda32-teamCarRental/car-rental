package pl.sdacademy.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.carrental.domain.cars.CarCategory;
import pl.sdacademy.carrental.domain.cars.FuelType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationListDetails {
   private String makeAndModel;
   private CarCategory carCategory;
   private FuelType fuelType;
   private int pricePerDay;
   private int rentalCost;
   private boolean isAutomatic;
}
