package pl.wartego.restauranttablesreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController

public class IndexController {
    @RequestMapping("/welcome")
   public String index (Model model){
        model.addAttribute("name","folks");
        return "index";
    }
}