package pl.sdacademy.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.carrental.domain.Branch;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.services.BranchService;
import pl.sdacademy.carrental.services.CompanyService;

@Controller
@RequestMapping("admin/company")
public class CompanyController {
    private final CompanyService companyService;
    private final BranchService branchService;

    public CompanyController(final CompanyService companyService, final BranchService branchService) {
        this.companyService = companyService;
        this.branchService = branchService;
    }

    @GetMapping("/branches")
    public String listAllBranches(final ModelMap modelMap) {
        modelMap.addAttribute("branches", companyService.getAllBranches());
        return "branches";
    }

    @GetMapping("/branches/create")
    public String showBranchForm(final ModelMap modelMap) {
        modelMap.addAttribute("branchForm", new BranchForm());
        return "branch-create";
    }

    @PostMapping("/branches/create")
    public String handleNewBranch(@ModelAttribute(name = "branchForm") final BranchForm branchForm) {
        branchService.createBranch(branchForm);
        return "redirect:/admin/company/branches";
    }

    @GetMapping("/branches/delete/{id}")
    public String deleteBranch(@PathVariable final Long id) {
        branchService.delete(id);
        return "redirect:/admin/company/branches";
    }

    @GetMapping("/branches/edit/{id}")
    public String showBranchEditForm(@PathVariable final Long id, final ModelMap modelMap) {
        final BranchForm branchForm = branchService.getById(id);

        modelMap.addAttribute("branchForm", branchForm);
        modelMap.addAttribute("branchId", id);
        return "branch-edit";
    }

    @PostMapping("/branches/edit/{id}")
    public String handleBranchEdit(@PathVariable final Long id, @ModelAttribute(name = "branchForm") final BranchForm branchForm) {
        branchService.updateBranch(id, branchForm);
        return "redirect:/admin/company/branches";
    }

}
