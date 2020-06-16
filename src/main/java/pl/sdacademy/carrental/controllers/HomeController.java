package pl.sdacademy.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.requests.CarReservationRequest;
import pl.sdacademy.carrental.services.BranchService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
   
   private final BranchService branchService;
   
   public HomeController(final BranchService branchService) {
      this.branchService = branchService;
   }
   
   @GetMapping
   public String hello(final Model model) {
      final LocalDate today = LocalDate.now();
      final List<Branch> branchList = branchService.getAll();
      model.addAttribute("today", today);
      model.addAttribute("carReservationRequest", new CarReservationRequest());
      model.addAttribute("branchList", branchList);
      return "home";
   }
   
}
