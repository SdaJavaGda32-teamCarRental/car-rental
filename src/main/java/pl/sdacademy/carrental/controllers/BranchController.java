package pl.sdacademy.carrental.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.carrental.domain.Branch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.carrental.services.BranchService;
import pl.sdacademy.carrental.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/admin/list-branches")
public class BranchController {

    private final BranchService branchService;
    private final CarService carService;

    public BranchController(BranchService branchService, CarService carService) {
        this.branchService = branchService;
        this.carService = carService;
    }

    @GetMapping
    public String listAllBranches(final Model model) {
        final List<Branch> allBranches = branchService.getAll();
        final boolean carsAvailability = carService.shouldNotifyAboutLowCarAvailability();
        model.addAttribute("branchList", allBranches);
        model.addAttribute("MinimumAvailableCarsNotification", carsAvailability);
        return "branches-list";
    }
}
