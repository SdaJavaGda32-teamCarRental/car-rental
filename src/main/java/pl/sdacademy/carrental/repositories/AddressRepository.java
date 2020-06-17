package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.carrental.domain.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM addresses a WHERE a.city = :city AND a.zip_code = :zipCode AND a.street = :street AND a.building = :building AND a.apartment = :apartment",
            nativeQuery = true)
    List<Address> findExistingAddress(
            @Param("city") String city,
            @Param("zip_code") String zipCode,
            @Param("street") String street,
            @Param("building") String building,
            @Param("apartment") String apartment);
}
