package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.exceptions.NotFoundException;
import com.example.touristguideapigruppe.exceptions.NullException;
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
    public String getByName(@PathVariable String name, Model model) throws NotFoundException {
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

    @GetMapping("handleAttraction/{name}")
    public String getHandleAttraction(@PathVariable("name") String name, Model model) throws NotFoundException {
        TouristAttraction result = touristService.getByName(name);
        model.addAttribute("attraction",
                result);
        return "handle-attraction";
    }

    @PostMapping("updateAttraction")
    public String updateAttraction(@ModelAttribute("attraction")TouristAttraction newAttraction) {
        touristService.updateAttraction(newAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        TouristAttraction result = touristService.deleteAttraction(name);
        return "redirect:/attractions";
    }
}
