package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    @GetMapping("{name}")
    public String getByName(@PathVariable String name, Model model) {
        model.addAttribute("specific", touristService.getByName(name));
        return "attraction-information";
    }

    @GetMapping("insertAttraction")
    public String getHandleAttraction(Model model){
        model.addAttribute("attraction",
                new TouristAttraction());
        return "add-attraction";
    }

    @PostMapping("addAttraction")
    public String addAttraction(@ModelAttribute("attraction")TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }
}
