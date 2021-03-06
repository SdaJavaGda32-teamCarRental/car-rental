package pl.sdacademy.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "employees")
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
   @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 10, initialValue = 1)
   private Long id;
   
   @Column(name = "first_name")
   private String firstName;
   
   @Column(name = "last_name")
   private String lastName;
   
   @Column(name = "role")
   @Enumerated(EnumType.STRING)
   private EmployeeRole role;
   
   @ManyToOne
   @JoinColumn(name = "branch_id")
   private Branch branch;
}
