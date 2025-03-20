package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.exceptions.NotFoundException;
import com.example.touristguideapigruppe.exceptions.UnknownErrorException;
import com.example.touristguideapigruppe.models.KeyValuePair;
import com.example.touristguideapigruppe.models.SaveTouristAttraction;
import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAttractions(Model model) {
        model.addAttribute("attractions",
                touristService.getAttractions());
        return "attractions";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable int id, Model model) throws NotFoundException {
        if(id <= 0) throw new IllegalArgumentException();

        model.addAttribute("specific",
                touristService.getById(id));
        return "attraction-information";
    }

    @GetMapping("{id}/tags")
    public String getTagDisplayName(@PathVariable("id") int id, Model model) throws NotFoundException {
        if(id == 0) throw new IllegalArgumentException();

        TouristAttraction result = touristService.getById(id);
        if(result == null)
            throw new UnknownErrorException();

        model.addAttribute("attraction",result);

        return "attraction-tags";
    }

    // henter layout til add
    @GetMapping("add")
    public String getAddAttraction(Model model) {
        model.addAttribute("attraction",
                new SaveTouristAttraction());
        model.addAttribute("tags", touristService.getTags());
        model.addAttribute("cities", touristService.getCities());

        return "add-attraction";
    }

    // indsætter en attraktion
    @PostMapping("save")
    public String addAttraction(@ModelAttribute("attraction") SaveTouristAttraction attraction) {
        if (attraction == null) throw new IllegalArgumentException();

        TouristAttraction result = touristService.addAttraction(attraction);
        if (result == null) throw new UnknownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    // henter layout for edit og slet
    @GetMapping("{id}/edit")
    public String getHandleAttraction(@PathVariable("id") int id, Model model) throws NotFoundException {
        if (id == 0) throw new IllegalArgumentException();

        model.addAttribute("attraction", getSaveAttraction(id));
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getTags());
        return "edit-attraction";
    }
    // Laver en SaveAttraction, så vi kan updatere en attraction.
    // Den kunne ikke finde ud af, at indsætte eksisterende værdier før jeg ændrede til SaveAttraction.
    private SaveTouristAttraction getSaveAttraction(int id) {
        TouristAttraction result = touristService.getById(id);

        // Setup tags for SaveAttraction
        List<Integer> tags = new ArrayList<>();
        for (KeyValuePair tag : result.getTags()) {
            tags.add(tag.getId());
        }

        return new SaveTouristAttraction(result.getId(),
                result.getName(),result.getDescription(),result.getCity().getId(), tags);
    }

    // opdaterer en attraktion
    @PostMapping("update")
    public String updateAttraction(@ModelAttribute("attraction") SaveTouristAttraction newAttraction) throws NotFoundException {
        if (newAttraction == null) throw new IllegalArgumentException();

        boolean result = touristService.updateAttraction(newAttraction);
        if(!result) throw new UnknownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    // sletter en attraktion
    @PostMapping("delete/{id}")
    public String deleteAttraction(@PathVariable int id) throws NotFoundException {
        if (id == 0) throw new IllegalArgumentException();

        boolean result = touristService.deleteAttraction(id);
        if (!result) throw new UnknownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }
}
