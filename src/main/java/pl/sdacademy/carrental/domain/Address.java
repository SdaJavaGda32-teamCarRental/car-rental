package pl.sdacademy.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

import javax.persistence.*;
import java.util.Objects;

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
