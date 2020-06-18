package pl.sdacademy.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.carrental.model.ReservationListDetails;
import pl.sdacademy.carrental.requests.CarReservationRequest;
import pl.sdacademy.carrental.services.ReservationService;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationsController {
   
   private final ReservationService reservationService;
   
   public ReservationsController(final ReservationService reservationService) {
      this.reservationService = reservationService;
   }
   
   @PostMapping
   public String showPageWithAvailableCarsGivenDateAndBranch(@ModelAttribute final CarReservationRequest reservationRequest,
                                                             final Model model) {
      final List<ReservationListDetails> upForReservation = reservationService.getCarsUpForReservation(reservationRequest);
      
      model.addAttribute("reservationRequest", reservationRequest);
      model.addAttribute("carList", upForReservation);
   
      System.out.println("upForReservation = " + upForReservation);
      
      return "reservation";
   }
   
}
