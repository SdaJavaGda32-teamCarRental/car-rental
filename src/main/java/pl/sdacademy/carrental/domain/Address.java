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
@Entity(name = "addresses")
public class Address {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addres_id_seq")
   @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 50, initialValue = 1)
   private Long id;
   
   @Column(name = "street")
   private String street;
   
   @Column(name = "building")
   private String building;
   
   @Column(name = "apartment")
   private String apartment;
   
   @Column(name = "zip_code")
   private String zipCode;
   
   @Column(name = "city")
   private String city;
}
