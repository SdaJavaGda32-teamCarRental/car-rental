package pl.sdacademy.carrental.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sdacademy.carrental.model.BranchForm;
import pl.sdacademy.carrental.model.CompanyForm;
import pl.sdacademy.carrental.repositories.LogotypeRepository;
import pl.sdacademy.carrental.services.BranchService;
import pl.sdacademy.carrental.services.CompanyService;

import java.io.IOException;

@Controller
@RequestMapping("admin/company")
public class CompanyController {
    private final CompanyService companyService;
    private final BranchService branchService;
    private final LogotypeRepository logotypeRepository;

    public CompanyController(final CompanyService companyService,
                             final BranchService branchService,
                             final LogotypeRepository logotypeRepository) {
        this.companyService = companyService;
        this.branchService = branchService;
        this.logotypeRepository = logotypeRepository;
    }

    @GetMapping
    public String editCompanyDetails(final ModelMap modelMap) {
        modelMap.addAttribute("companyForm", companyService.getCompanyForm());
        modelMap.addAttribute("logotypeIds", companyService.getAllLogotypeIds());
        return "company";
    }

    @PostMapping("/edit")
    public String handleCompanyEdit(@ModelAttribute(name = "companyForm") final CompanyForm companyForm){
        companyService.update(companyForm);
        return "redirect:/admin/company";
    }

    @GetMapping(value = "/logo/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] showLogotype(@PathVariable final Long id) {
        return logotypeRepository.findById(id).orElseThrow().getImage();
    }

    @PostMapping("/uploadLogo")
    public String uploadLogotype(@RequestParam("imageFile") final MultipartFile imageFile) throws IOException {
        companyService.saveImage(imageFile);
        return "redirect:/admin/company/";
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
