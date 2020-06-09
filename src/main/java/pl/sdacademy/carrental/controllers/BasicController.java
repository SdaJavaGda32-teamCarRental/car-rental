package pl.sdacademy.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.carrental.domain.cars.Car;
import pl.sdacademy.carrental.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/list-cars")
public class BasicController {
   
   private final CarService carService;
   
   public BasicController(final CarService carService) {
      this.carService = carService;
   }
   
   @GetMapping
   public String listAllCars(final Model model) {
      final List<Car> allCars = carService.getAll();
      final List<Car> available = carService.getAvailable();
      model.addAttribute("carList", allCars);
      model.addAttribute("availableCars", available);
      return "car-list";
   }
   
}
