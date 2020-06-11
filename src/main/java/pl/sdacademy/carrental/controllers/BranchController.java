package pl.sdacademy.carrental.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.carrental.domain.Branch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.carrental.domain.Employee;
import pl.sdacademy.carrental.domain.EmployeeRole;
import pl.sdacademy.carrental.services.BranchService;
import pl.sdacademy.carrental.services.CarService;
import pl.sdacademy.carrental.services.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/list-branches")
public class BranchController {

    private final BranchService branchService;
    private final CarService carService;
    private final EmployeeService employeeService;

    public BranchController(BranchService branchService, CarService carService, EmployeeService employeeService) {
        this.branchService = branchService;
        this.carService = carService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listAllBranches(final Model model) {
        final List<Branch> allBranches = branchService.getAll();
        final boolean notification = carService.availableOnlyOneOrTwoCars();
//        final List<Employee> managerList = employeeService.getManager();
        model.addAttribute("branchList", allBranches);
//        model.addAttribute("managerList",managerList);
        model.addAttribute("oneOrTwoCarsNotification",notification);
        return "branches-list";
    }
}
