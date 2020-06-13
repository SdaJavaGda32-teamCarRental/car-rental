package pl.sdacademy.carrental.domain;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "client")
public class Client {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "first_name")
   private String firstName;
   
   @Column(name = "last_name")
   private String lastName;
   
   @Column(name = "email")
   @Email
   @NotNull
   private String email;
   
   @OneToOne
   @JoinColumn(name = "address_id")
   private Address address;
   
   @Column(name = "phone_number")
   @Pattern(regexp = "\\d{9}")
   private String phoneNumber;
}
