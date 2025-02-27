package com.example.touristguideapigruppe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "aboutUs"; // Skal matche HTML-filen i templates-mappen
    }
    @GetMapping("kontakt-os")
    public String getContactPage(Model model) {
        model.addAttribute("pageTitle", "Kontakt os");
        return "contact";
    }

}
