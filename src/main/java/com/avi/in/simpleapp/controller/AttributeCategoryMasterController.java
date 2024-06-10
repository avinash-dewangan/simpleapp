package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.entity.AttributeCategoryMaster;
import com.avi.in.simpleapp.entity.LocationTypeMaster;
import com.avi.in.simpleapp.repository.AttributeCategoryMasterRepository;
import com.avi.in.simpleapp.repository.LocationTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attribute-category")
public class AttributeCategoryMasterController {

    @Autowired
    private AttributeCategoryMasterRepository repository;

    @GetMapping
    public String listAll(Model model) {
        List<AttributeCategoryMaster> attributeCategoryMaster = repository.findAll();
        model.addAttribute("attributeCategoryMaster", attributeCategoryMaster);
        return "attribute-category/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("attributeCategoryMaster", new AttributeCategoryMaster());
        return "attribute-category/form";
    }

    @PostMapping
    public String create(@ModelAttribute AttributeCategoryMaster attributeCategoryMaster) {
        repository.save(attributeCategoryMaster);
        return "redirect:/attribute-category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AttributeCategoryMaster attributeCategoryMaster = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        model.addAttribute("attributeCategoryMaster", attributeCategoryMaster);
        return "attribute-category/form";
    }

    @PostMapping("/{id}")
    public String updateById(@PathVariable Long id, @ModelAttribute AttributeCategoryMaster attributeCategoryMaster) {
        attributeCategoryMaster.setAttributeCategoryMasterId(id);
        repository.save(attributeCategoryMaster);
        return "redirect:/attribute-category";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        AttributeCategoryMaster attributeCategoryMaster = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        repository.delete(attributeCategoryMaster);
        return "redirect:/attribute-category";
    }
}

