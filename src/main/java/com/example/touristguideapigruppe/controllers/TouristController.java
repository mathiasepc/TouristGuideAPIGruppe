package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.exceptions.NotFoundException;
import com.example.touristguideapigruppe.exceptions.NullException;
import com.example.touristguideapigruppe.exceptions.UnkownErrorException;
import com.example.touristguideapigruppe.models.Tags;
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

    // hent "index" til attraction
    @GetMapping("")
    public String getAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAttractions();
        model.addAttribute("attractions", attractions);
        return "attractions";
    }

    // hente specifik
    @GetMapping("{name}")
    public String getByName(@PathVariable String name, Model model) throws NotFoundException {
        model.addAttribute("specific", touristService.getByName(name));
        return "attraction-information";
    }

    // henter add layout
    @GetMapping("insertAttraction")
    public String getAddAttraction(Model model) {
        List<Tags> availableTags = Arrays.asList(Tags.values());
        model.addAttribute("availableTags", availableTags);
        model.addAttribute("attraction",
                new TouristAttraction());

        return "add-attraction";
    }

    // tilføj metoden
    @PostMapping("addAttraction")
    public String addAttraction(@ModelAttribute("attraction") TouristAttraction attraction, @RequestParam("tags") List<Tags> tags) {
        if (attraction == null) throw new NullException();

        attraction.setTags(tags);
        TouristAttraction result = touristService.addAttraction(attraction);
        if (result == null) throw new UnkownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    // henter update/delete layout
    @GetMapping("handleAttraction/{name}")
    public String getHandleAttraction(@PathVariable("name") String name, Model model) throws NotFoundException {
        if (name == null) throw new NullException();

        TouristAttraction result = touristService.getByName(name);
        model.addAttribute("attraction", result);

        List<Tags> availableTags = Arrays.asList(Tags.values());
        model.addAttribute("availableTags", availableTags);

        return "handle-attraction";
    }

    // opdater metoden
    @PostMapping("updateAttraction")
    public String updateAttraction(@ModelAttribute("attraction") TouristAttraction newAttraction, @RequestParam ("tags") List<Tags> tags) throws NotFoundException {
        if (newAttraction == null) throw new NullException();

        newAttraction.setTags(tags);
        TouristAttraction result = touristService.updateAttraction(newAttraction);
        if (result == null) throw new UnkownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    // delete metoden
    @PostMapping("delete/{name}")
    public String deleteAttraction(@PathVariable String name) throws NotFoundException {
        if (name == null) throw new NullException();

        TouristAttraction result = touristService.deleteAttraction(name);
        if (result == null) throw new UnkownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    //
    @GetMapping("{name}/tags")
    public String getTagsByAttraction(@PathVariable String name, Model model) throws NotFoundException{
        if (name == null) throw new NullException();

        TouristAttraction attraction = touristService.getByName(name);

        if (attraction == null) {
            throw new NotFoundException("Attraction not found");
        }

        model.addAttribute("specific", attraction);
        return "attraction-tags";
    }

}
