package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.entity.LocationTypeMaster;
import com.avi.in.simpleapp.repository.LocationTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/location-types")
public class LocationTypeMasterController {

    @Autowired
    private LocationTypeMasterRepository repository;

    @GetMapping
    public String listLocationTypes(Model model) {
        List<LocationTypeMaster> locationTypes = repository.findAll();
        model.addAttribute("locationTypes", locationTypes);
        return "location-types/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("locationType", new LocationTypeMaster());
        return "location-types/form";
    }

    @PostMapping
    public String createLocationType(@ModelAttribute LocationTypeMaster locationType) {
        repository.save(locationType);
        return "redirect:/location-types";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        LocationTypeMaster locationType = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        model.addAttribute("locationType", locationType);
        return "location-types/form";
    }

    @PostMapping("/{id}")
    public String updateLocationType(@PathVariable Long id, @ModelAttribute LocationTypeMaster locationType) {
        locationType.setLocationTypeMasterId(id);
        repository.save(locationType);
        return "redirect:/location-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocationType(@PathVariable Long id) {
        LocationTypeMaster locationType = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        repository.delete(locationType);
        return "redirect:/location-types";
    }
}

