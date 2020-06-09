package pl.sdacademy.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.carrental.domain.cars.Car;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "branches")
public class Branch {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String city;
   
   @OneToOne
   private Address address;
   
   @OneToMany(mappedBy = "currentBranch")
   private List<Car> carsOnHand;
   
   @OneToMany(mappedBy = "branch")
   private List<Employee> employees = new ArrayList<>();
}
