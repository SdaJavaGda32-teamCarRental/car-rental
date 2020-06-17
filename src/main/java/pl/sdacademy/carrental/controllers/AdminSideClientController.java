package pl.sdacademy.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import pl.sdacademy.carrental.model.ClientForm;
import pl.sdacademy.carrental.services.ClientService;

// (PIOTR) i'm not sure how to split admin-only methods and those available for users. From what i understand with Profile button you can
// check your own profile as a certain user, but users list should be available only for admins? So different controller
// for everything single user related?

@Controller
@RequestMapping("/admin/clients")
public class AdminSideClientController {

    private final ClientService clientService;

    public AdminSideClientController(final ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping()
    public String listAllClients(final ModelMap modelMap) {
        modelMap.addAttribute("clients", clientService.getAll());
        return "clients";
    }

    @GetMapping("/create")
    public String showClientForm(final ModelMap modelMap) {
        modelMap.addAttribute("clientForm", new ClientForm());
        return "client-create";
    }

    @PostMapping("/create")
    public String handleNewClient(@ModelAttribute(name = "clientForm") final ClientForm clientForm) {
        clientService.createClient(clientForm);
        return "redirect:/admin/clients";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable final Long id) {
        clientService.delete(id);
        return "redirect:/admin/clients";
    }

    @GetMapping("/edit/{id}")
    public String showClientEditForm(@PathVariable final Long id, final ModelMap modelMap) {

        final ClientForm clientForm = clientService.getById(id);
        modelMap.addAttribute("clientForm", clientForm);
        modelMap.addAttribute("clientId", id);
        return "client-edit";
    }

    @PostMapping("/edit/{id}")
    public String handleClientEdit(@PathVariable final Long id, @ModelAttribute(name = "branchForm") final ClientForm clientForm) {
        clientService.updateClient(id, clientForm);
        return "redirect:/admin/clients";
    }
}
