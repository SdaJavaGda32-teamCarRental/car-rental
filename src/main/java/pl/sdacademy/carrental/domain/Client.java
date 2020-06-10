package pl.sdacademy.carrental.domain;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
   private String firstName;
   private String lastName;
   private String email;
   private Address address;
   private String phoneNumber;
}
