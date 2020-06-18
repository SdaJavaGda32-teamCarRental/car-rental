package pl.sdacademy.carrental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.CarCategory;
import pl.sdacademy.carrental.domain.rentals.Reservation;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
   
   @Query("SELECT COUNT(r) FROM reservations r WHERE " +
         ":category = r.carCategory " +
         "AND (:start BETWEEN r.pickupDate AND r.returnDate)" +
         "AND (:end BETWEEN r.pickupDate AND r.returnDate)" +
         "AND (:start <= r.pickupDate AND :end >= r.returnDate)")
   int countReservationsForCategoryInGivenDates(@Param("category") CarCategory category,
                                                @Param("start") LocalDate start,
                                                @Param("end") LocalDate end);
      
   
   @Query("SELECT COUNT(r) FROM reservations r WHERE " +
         "r.carCategory = :category " +
         "AND r.pickupBranch = :pickupBranch " +
         "AND r.pickupDate >= :start " +
         "AND r.pickupDate <= :end")
   Integer countCarLeavingBranchBetweenDates(@Param("category") CarCategory category,
                                             @Param("pickupBranch") Branch pickupBranch,
                                             @Param("start") LocalDate start,
                                             @Param("end") LocalDate end);
   
   @Query("SELECT COUNT(r) FROM reservations r WHERE " +
         "r.carCategory = :category " +
         "AND r.returnBranch = :returnBranch " +
         "AND r.returnDate >= :start " +
         "AND r.returnDate <= :end")
   Integer countCarsReturningBranchBetweenDates(@Param("category") CarCategory category,
                                                @Param("returnBranch") Branch returnBranch,
                                                @Param("start") LocalDate start,
                                                @Param("end") LocalDate end);
   
   
}
