package pl.sdacademy.carrental.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarReservationRequest {
   private String pickupBranchName;
   private String pickupDate;
   private String returnBranchName;
   private String returnDate;
   
}
