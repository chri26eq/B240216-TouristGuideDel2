package com.example.touristguide.controller;

import com.example.touristguide.model.Attraction;
import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("attractions") //URL endpoint
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getAllAttractions(Model model) {
        List<Attraction> attractionList = service.getAttractionList();
        model.addAttribute("attractionList", attractionList);
        return "attractionListing";
    }


    @GetMapping("/{name}")
    public String findAttractionByName(@PathVariable("name") String name, Model model) {
        Attraction attraction = service.findByName(name);
        model.addAttribute("attraction", attraction);

        return "attractionDetails";
    }

    @GetMapping("/tags/{name}")
    public String getAttractionTags(@PathVariable("name") String name, Model model) {
        Attraction attraction = service.findByName(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        List<String> allTags = service.getAllTags();
        List<String> allTowns = service.getAllTowns();
        model.addAttribute("allTags", allTags);
        model.addAttribute("allTowns", allTowns);
        return "add";
    }


    @PostMapping("/save")
    public String addAttraction(
            @ModelAttribute Attraction attraction) {

        service.addAttraction(attraction);

        return "redirect:/attractions/attractionAdded";
    }

    @GetMapping("/attractionAdded")
    public String showAddedAttraction() {
        return "added";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable("name") String name, Model model) {
        Attraction attraction = service.findByName(name);
        List<String> allTags = service.getAllTags();
        List<String> allTowns = service.getAllTowns();
        model.addAttribute("attraction", attraction);
        model.addAttribute("allTags", allTags);
        model.addAttribute("allTowns", allTowns);
        return "edit";
    }

    @PostMapping("/update")
    public String updateAttraction(
            @ModelAttribute Attraction attraction) {
        service.updateAttraction(attraction);
        return "redirect:/attractions/attractionUpdated";
    }

    @GetMapping("/attractionUpdated")
    public String showUpdatedAttraction() {
        return "updated";
    }

    @GetMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable("name") String name) {
        service.deleteAttraction(name);
        return "deleted";
    }
}
