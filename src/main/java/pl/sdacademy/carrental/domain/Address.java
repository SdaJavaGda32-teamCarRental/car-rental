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
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "street")
   private String street;
   
   @Column(name = "building")
   private String building;
   
   @Column(name = "apartment")
   private String apartment;
   
   @Column(name = "zip_code")
   private String zip;
   
   @Column(name = "city")
   private String city;
}
