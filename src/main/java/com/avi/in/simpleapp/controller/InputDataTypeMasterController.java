package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.entity.AttributeCategoryMaster;
import com.avi.in.simpleapp.entity.InputDataTypeMaster;
import com.avi.in.simpleapp.repository.AttributeCategoryMasterRepository;
import com.avi.in.simpleapp.repository.InputDataTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/inputDataType")
public class InputDataTypeMasterController {

    @Autowired
    private InputDataTypeMasterRepository repository;

    @GetMapping
    public String listAll(Model model) {
        List<InputDataTypeMaster> inputDataTypeMaster = repository.findAll();
        model.addAttribute("inputDataTypeMaster", inputDataTypeMaster);
        return "inputDataType/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("inputDataTypeMaster", new InputDataTypeMaster());
//        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
//        model.addAttribute("options", options);
        return "inputDataType/form";
    }

    @PostMapping
    public String create(@ModelAttribute InputDataTypeMaster inputDataTypeMaster) {
        repository.save(inputDataTypeMaster);
        return "redirect:/inputDataType";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        InputDataTypeMaster inputDataTypeMaster = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        model.addAttribute("inputDataTypeMaster", inputDataTypeMaster);
        return "inputDataType/form";
    }

    @PostMapping("/{id}")
    public String updateById(@PathVariable Long id, @ModelAttribute InputDataTypeMaster inputDataTypeMaster) {
        inputDataTypeMaster.setInputDataTypeMasterId(id);
        repository.save(inputDataTypeMaster);
        return "redirect:/inputDataType";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        InputDataTypeMaster inputDataTypeMaster = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        repository.delete(inputDataTypeMaster);
        return "redirect:/inputDataType";
    }
}

