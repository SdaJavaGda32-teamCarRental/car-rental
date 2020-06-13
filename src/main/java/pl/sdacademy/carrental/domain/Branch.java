package pl.sdacademy.carrental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_seq")
   @SequenceGenerator(name = "branch_id_seq", sequenceName = "branch_id_seq", allocationSize = 10, initialValue = 1)
   private Long id;
   
   @Column(name = "name")
   private String name;
   
   @OneToOne
   @JoinColumn(name = "address_id")
   private Address address;

   @Enumerated(EnumType.STRING)
   private BranchStatus status;

   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @OneToMany(mappedBy = "currentBranch")
   @JsonIgnore
   private List<Car> carsOnHand = new ArrayList<>();

   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @JsonIgnore
   @OneToMany(mappedBy = "branch")
   private List<Employee> employees = new ArrayList<>();
}
