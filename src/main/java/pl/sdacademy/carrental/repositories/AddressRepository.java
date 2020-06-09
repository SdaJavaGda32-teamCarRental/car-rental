package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
