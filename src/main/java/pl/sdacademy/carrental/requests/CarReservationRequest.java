package pl.sdacademy.carrental.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class CarReservationRequest {
    @NotBlank
    private String pickupBranchName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;

    @NotBlank
    private String returnBranchName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @AssertTrue(message = "Pickup date must be future or present")
    private boolean isPickupDateFutureOrPresent() {
        return pickupDate.isAfter(LocalDate.now().minusDays(1));
    }

    @AssertTrue(message = "Return must occur after pickup")
    private boolean isReturnDateAfterPickup() {
        return returnDate.isAfter(pickupDate.minusDays(1));
    }


}
