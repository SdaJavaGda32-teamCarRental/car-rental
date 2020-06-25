package pl.sdacademy.carrental.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "clients")
public class Client {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
   @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq")
   private Long id;
   
   @Column(name = "first_name")
   @NotBlank
   private String firstName;
   
   @Column(name = "last_name")
   @NotBlank
   private String lastName;
   
   @Column(name = "email")
   @Email
   @NotNull
   private String email;
   
   @OneToOne
   @JoinColumn(name = "address_id")
   private Address address;
   
   @Column(name = "phone_number")
   @NotNull
   @Digits(integer = 9, fraction = 0, message = "Phone number must consist of 9 digits")
   private String phoneNumber;
}
