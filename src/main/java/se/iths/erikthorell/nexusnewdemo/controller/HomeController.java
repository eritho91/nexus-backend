package se.iths.erikthorell.nexusnewdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//REDIRECTS TO OPENAPI

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui/index.html";
    }
}