package pl.sdacademy.carrental.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class CarReservationRequest {
   @NotNull
   private String pickupBranchName;
   
   @NotNull
   private String pickupDate;
   
   @NotNull
   private String returnBranchName;
   
   @NotNull
   private String returnDate;
   
   @AssertTrue(message = "Pickup date must be future or present")
   private boolean isPickupDateFutureOrPresent() {
      return LocalDate.parse(pickupDate).isAfter(LocalDate.now().minusDays(1));
   }
   
   @AssertTrue(message = "Return must occur after pickup")
   private boolean isReturnDateAfterPickup() {
      return LocalDate.parse(returnDate).isAfter(LocalDate.parse(pickupDate).minusDays(1));
   }
   
   
}
