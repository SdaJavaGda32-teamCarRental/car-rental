package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.domain.cars.CarCategory;
import pl.sdacademy.carrental.mappers.CarToReservationListItemDetailsMapper;
import pl.sdacademy.carrental.model.ReservationListDetails;
import pl.sdacademy.carrental.repositories.ReservationRepository;
import pl.sdacademy.carrental.requests.CarReservationRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ReservationService {
   
   private final ReservationRepository reservationRepo;
   private final BranchService branchService;
   private final CarService carService;
   private final CarToReservationListItemDetailsMapper carToReservationListItemMapper;
   
   public ReservationService(final ReservationRepository reservationRepo,
                             final BranchService branchService,
                             final CarService carService,
                             final CarToReservationListItemDetailsMapper carToReservationListItemMapper) {
      this.reservationRepo = reservationRepo;
      this.branchService = branchService;
      this.carService = carService;
      this.carToReservationListItemMapper = carToReservationListItemMapper;
   }
   
   public List<ReservationListDetails> getCarsUpForReservation(final CarReservationRequest request) {
      Map<CarCategory, Integer> numberOfAvailableCarsPerCategoryInPeriod = new HashMap<>();
      
      Stream.of(CarCategory.values()).forEach(category ->
            putCarCountAvailableInBranchAndDatesPerCategory(numberOfAvailableCarsPerCategoryInPeriod,
                  request, category));
      
      final List<Car> sampleCarsList = convertToSampleCarsList(numberOfAvailableCarsPerCategoryInPeriod);
      
      return getReservationOfferListItems(request, sampleCarsList);
   }
   
   private void putCarCountAvailableInBranchAndDatesPerCategory(final Map<CarCategory, Integer> numberOfAvailableCarsPerCategoryInPeriod,
                                                                final CarReservationRequest request,
                                                                final CarCategory category) {
      
      final Branch pickupBranch = branchService.findBranchByName(request.getPickupBranchName());
      final LocalDate pickupDate = LocalDate.parse(request.getPickupDate());
      final LocalDate returnDate = LocalDate.parse(request.getReturnDate());
      
      int numberOfCarsOnDate = getNumberOfCarsOnDate(pickupBranch, pickupDate, category);
      
      int carsAvailableInDateRangeAndBranch = getLowestNumberOfAvailableCars(pickupBranch, pickupDate, returnDate, category, numberOfCarsOnDate);
      if (carsAvailableInDateRangeAndBranch > 0) {
         numberOfAvailableCarsPerCategoryInPeriod.put(category, carsAvailableInDateRangeAndBranch);
      }
   }
   
   private int getNumberOfCarsOnDate(final Branch pickupBranch, final LocalDate pickupDate, final CarCategory category) {
      final int numberOfCarsToday = carService.getNumberOfCarsAvailableInBranch(category, pickupBranch);
      final int carsLeaving = getNumberOfCarsLeavingBranchBeforeDate(category, pickupBranch, LocalDate.now(), pickupDate);
      final int carsReturning = getNumberOfCarsReturningBranchBeforeDate(category, pickupBranch, LocalDate.now(), pickupDate);
      
      return numberOfCarsToday - carsLeaving + carsReturning;
   }
   
   private List<Car> convertToSampleCarsList(final Map<CarCategory, Integer> numberOfAvailableCarsPerCategoryInPeriod) {
      return numberOfAvailableCarsPerCategoryInPeriod.keySet().stream()
            .map(carService::findSampleCarByCategory)
            .collect(Collectors.toList());
   }
   
   private List<ReservationListDetails> getReservationOfferListItems(final CarReservationRequest request, final List<Car> carList) {
      return carList.stream()
            .map(car -> carToReservationListItemMapper.mapCarToListItem(car, request))
            .collect(Collectors.toList());
   }
   
   private int getLowestNumberOfAvailableCars(final Branch pickupBranch,
                                              final LocalDate pickupDate,
                                              final LocalDate returnDate,
                                              final CarCategory category,
                                              int numberOfCarsOnDate) {
      
      int lowestNumberOfAvailableCars = numberOfCarsOnDate;
      
      for (LocalDate date = pickupDate; date.isEqual(returnDate); date = date.plusDays(1)) {
         numberOfCarsOnDate = numberOfCarsOnDate
               - getNumberOfCarsLeavingBranchBeforeDate(category, pickupBranch, date, date)
               + getNumberOfCarsReturningBranchBeforeDate(category, pickupBranch, date, date);
         
         if (numberOfCarsOnDate < lowestNumberOfAvailableCars) {
            lowestNumberOfAvailableCars = numberOfCarsOnDate;
         }
      }
      return lowestNumberOfAvailableCars;
   }
   
   private int getNumberOfCarsReturningBranchBeforeDate(final CarCategory category, final Branch pickupBranch, final LocalDate start, final LocalDate end) {
      return reservationRepo.countCarsReturningBranchBetweenDates(category, pickupBranch, start, end);
   }
   
   private int getNumberOfCarsLeavingBranchBeforeDate(final CarCategory category, final Branch branch, final LocalDate start, final LocalDate end) {
      return reservationRepo.countCarLeavingBranchBetweenDates(category, branch, start, end);
   }
   
}
