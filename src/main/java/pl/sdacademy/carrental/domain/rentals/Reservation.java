package pl.sdacademy.carrental.domain.rentals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.Client;
import pl.sdacademy.carrental.domain.cars.CarCategory;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "reservations")
public class Reservation {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_id_seq")
   @SequenceGenerator(name = "reservation_id_seq", sequenceName = "reservation_id_seq")
   private Long id;
   
   @CreationTimestamp
   @Column(name = "reservation_date")
   private LocalDateTime reservationDate;
   
   @Column(name = "car_category")
   @Enumerated(EnumType.STRING)
   @NotNull
   private CarCategory carCategory;
   
   @ManyToOne
   @JoinColumn(name = "client_id")
   @NotNull
   private Client client;
   
   @Column(name = "pickup_date")
   @FutureOrPresent
   @NotNull
   private LocalDate pickupDate;
   
   @Column(name = "return_date")
   @NotNull
   private LocalDate returnDate;
   
   @ManyToOne
   @JoinColumn(name = "pickup_branch")
   @NotNull
   private Branch pickupBranch;
   
   @ManyToOne
   @JoinColumn(name = "return_branch")
   @NotNull
   private Branch returnBranch;
   
   @Column(name = "amount")
   @NotNull
   private int payableAmount;
   
   @AssertTrue(message = "Return date must be after pickup date")
   private boolean isReturnAfterPickup() {
      if (returnDate == null || pickupDate == null) {
         return false;
      }
      return returnDate.isAfter(pickupDate.minusDays(1));
   }
   
}
