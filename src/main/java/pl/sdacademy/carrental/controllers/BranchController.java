package pl.sdacademy.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.services.BranchService;
import pl.sdacademy.carrental.services.CarService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class BranchController {
   
   private final BranchService branchService;
   
   public BranchController(final BranchService branchService) {
      this.branchService = branchService;
   }
   
   @GetMapping("/list-branches")
   public String listAllBranches(final Model model) {
      final Map<Branch, Integer> branchesWithCarCount = branchService.getBranchesWithCarCount();
      model.addAttribute("branchList", branchesWithCarCount);
      model.addAttribute("MinimumAvailableCars", BranchService.MINIMUM_CAR_COUNT);
      return "branches-list";
   }
}
