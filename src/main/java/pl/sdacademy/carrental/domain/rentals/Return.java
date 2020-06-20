package pl.sdacademy.carrental.domain.rentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.sdacademy.carrental.domain.Employee;
import pl.sdacademy.carrental.domain.cars.Car;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "returns")
public class Return {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "return_id_seq")
   @SequenceGenerator(name = "return_id_seq", sequenceName = "return_id_seq")
   private Long id;
   
   @ManyToOne
   @JoinColumn(name = "employee_id")
   private Employee employee;
   
   @CreationTimestamp
   @Column(name = "return_date_time")
   private LocalDateTime returnDateTime;

   @OneToOne
   @JoinColumn(name = "reservation_id")
   private Reservation reservation;
   
   @ManyToOne
   @JoinColumn(name = "car_id")
   private Car car;
   
   @Column(name = "additional_payment")
   private int additionalPayment;
   
   @Column(name = "remarks", length = 500)
   private String remarks;
}
