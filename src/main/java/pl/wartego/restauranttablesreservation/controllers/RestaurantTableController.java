package pl.wartego.restauranttablesreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wartego.restauranttablesreservation.models.RestaurantTable;
import pl.wartego.restauranttablesreservation.repository.RestaurantTableRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/tables")

public class RestaurantTableController {
    private final RestaurantTableRepository repository;

    public RestaurantTableController(RestaurantTableRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String tables(Model model) {
        model.addAttribute(new RestaurantTable());
        model.addAttribute("tables", repository.findAll());
        return "tables";
    }

    //    @PostMapping
//    public String tables(RestaurantTable table){
//        repository.save(table);
//        return "redirect:/tables";
//    }
    @PostMapping
    public String createTable(@Valid RestaurantTable table, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("tables", repository.findAll());
            return "tables";
        } else {
            repository.save(table);
            return "redirect:/tables";
        }
    }


}
