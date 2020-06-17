package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
