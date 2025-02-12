package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/{name}")
    public ResponseEntity<TouristAttraction> getByName(@PathVariable String name) {
        return name == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(touristService.getByName(name));
    }
}
