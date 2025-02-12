package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.exceptions.NotFoundException;
import com.example.touristguideapigruppe.exceptions.NullException;
import com.example.touristguideapigruppe.exceptions.UnkownErrorException;
import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
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
    public String getAddAttraction(Model model) {
        model.addAttribute("attraction",
                new TouristAttraction());
        return "add-attraction";
    }

    @PostMapping("addAttraction")
    public String addAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        if (attraction == null) throw new NullException();

        TouristAttraction result = touristService.addAttraction(attraction);
        if (result == null) throw new UnkownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    @GetMapping("handleAttraction/{name}")
    public String getHandleAttraction(@PathVariable("name") String name, Model model) throws NotFoundException {
        if (name == null) throw new NullException();

        TouristAttraction result = touristService.getByName(name);
        model.addAttribute("attraction",
                result);
        return "handle-attraction";
    }

    @PostMapping("updateAttraction")
    public String updateAttraction(@ModelAttribute("attraction") TouristAttraction newAttraction) throws NotFoundException {
        if (newAttraction == null) throw new NullException();


        TouristAttraction result = touristService.updateAttraction(newAttraction);
        if(result == null) throw new UnkownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }

    @PostMapping("delete/{name}")
    public String deleteAttraction(@PathVariable String name) throws NotFoundException {
        if (name == null) throw new NullException();

        TouristAttraction result = touristService.deleteAttraction(name);
        if (result == null) throw new UnkownErrorException();

        // laver en 302 response sådan, at ikke kan poste det samme igen.
        return "redirect:/attractions";
    }
}
