package pl.sdacademy.carrental.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "clients")
public class Client {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
   @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 50, initialValue = 1)
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
