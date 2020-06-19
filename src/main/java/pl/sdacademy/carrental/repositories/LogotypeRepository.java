package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.carrental.domain.Logotype;

public interface LogotypeRepository extends JpaRepository<Logotype,Long> {
}
