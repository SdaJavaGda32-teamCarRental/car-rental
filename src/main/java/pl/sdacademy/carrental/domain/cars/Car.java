package pl.sdacademy.carrental.domain.cars;

import lombok.*;
import pl.sdacademy.carrental.domain.Branch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cars")
public class Car {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "plate_number")
   private String plateNumber;
   
   @Column(name = "make")
   private String make;
   
   @Column(name = "model")
   private String model;
   
   @Column(name = "production_year")
   private int productionYear;
   
   @Column(name = "fuel_type")
   @Enumerated(EnumType.STRING)
   private FuelType fuelType;
   
   @Column(name = "color")
   private String color;
   
   @NotNull
   @Column(name = "price")
   private int rentPrice;
   
   @Column(name = "mileage")
   private int mileage;
   
   @ManyToOne
   @JoinColumn(name = "branch_id")
   private Branch currentBranch;
   
   @Column(name = "status")
   private Status currentStatus;
   
}
