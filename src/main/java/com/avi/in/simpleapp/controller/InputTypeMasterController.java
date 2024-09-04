package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.entity.InputDataTypeMaster;
import com.avi.in.simpleapp.entity.InputTypeMaster;
import com.avi.in.simpleapp.repository.InputDataTypeMasterRepository;
import com.avi.in.simpleapp.repository.InputTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/input-type")
public class InputTypeMasterController {

    @Autowired
    private InputTypeMasterRepository repository;

    @GetMapping
    public String listAll(Model model) {
        List<InputTypeMaster> inputTypeMaster = repository.findAll();
        model.addAttribute("inputTypeMaster", inputTypeMaster);
        return "input-type/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("inputTypeMaster", new InputTypeMaster());
//        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
//        model.addAttribute("options", options);
        return "input-type/form";
    }

    @PostMapping
    public String create(@ModelAttribute InputTypeMaster inputTypeMaster) {
        repository.save(inputTypeMaster);
        return "redirect:/input-type";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        InputTypeMaster inputTypeMaster = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        model.addAttribute("inputTypeMaster", inputTypeMaster);
        return "input-type/form";
    }

    @PostMapping("/{id}")
    public String updateById(@PathVariable Long id, @ModelAttribute InputTypeMaster inputTypeMaster) {
        inputTypeMaster.setInputTypeId(id);
        repository.save(inputTypeMaster);
        return "redirect:/input-type";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        InputTypeMaster inputTypeMaster = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        repository.delete(inputTypeMaster);
        return "redirect:/input-type";
    }
}

