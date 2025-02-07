package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @RequestMapping("")
    public ResponseEntity<List<TouristAttraction>> getAttractions() {
        List<TouristAttraction> attractions = touristService.getAttractions();
        return attractions.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(attractions);
    }
}
