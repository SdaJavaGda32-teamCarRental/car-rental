package pl.sdacademy.carrental.domain.cars;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_seq")
   @SequenceGenerator(name = "car_id_seq", sequenceName = "car_id_seq", allocationSize = 50, initialValue = 1)
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
   @Enumerated(EnumType.STRING)
   private Status currentStatus;
   
   @Enumerated(EnumType.STRING)
   @Column(name = "category")
   private CarCategory category;
   
   @Column(name = "transmission_auto")
   private boolean isAutomatic;
}
