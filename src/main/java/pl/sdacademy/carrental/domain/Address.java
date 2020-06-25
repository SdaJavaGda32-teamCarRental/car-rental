package pl.sdacademy.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "addresses")
@Validated
public class Address {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addres_id_seq")
   @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq")
   private Long id;
   
   @NotBlank
   @Column(name = "street")
   private String street;
   
   @NotBlank
   @Column(name = "building")
   private String building;
   
   @Column(name = "apartment")
   private String apartment;
   
   @NotBlank
   @Column(name = "zip_code")
   private String zipCode;
   
   @NotBlank
   @Column(name = "city")
   private String city;

   @Override
   public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      final Address address = (Address) o;
      return street.equals(address.street) &&
              building.equals(address.building) &&
              Objects.equals(apartment, address.apartment) &&
              zipCode.equals(address.zipCode) &&
              city.equals(address.city);
   }

   @Override
   public int hashCode() {
      return Objects.hash(street, building, apartment, zipCode, city);
   }
}
