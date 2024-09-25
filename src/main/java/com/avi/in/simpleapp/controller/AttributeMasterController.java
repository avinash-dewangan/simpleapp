package com.avi.in.simpleapp.controller;

import com.avi.in.simpleapp.dto.AttributeDTO;
import com.avi.in.simpleapp.dto.SelectedOption;
import com.avi.in.simpleapp.entity.Attribute;
import com.avi.in.simpleapp.entity.AttributeCategoryMaster;
import com.avi.in.simpleapp.entity.InputDataTypeMaster;
import com.avi.in.simpleapp.repository.AttributeCategoryMasterRepository;
import com.avi.in.simpleapp.repository.AttributeRepository;
import com.avi.in.simpleapp.repository.InputDataTypeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attribute")
public class AttributeMasterController {

    @Autowired
    private AttributeRepository repository;
    @Autowired
    private AttributeCategoryMasterRepository attributeCategoryMasterRepository;
    @Autowired
    private InputDataTypeMasterRepository inputDataTypeMasterRepository;

    @GetMapping
    public String listAll(Model model) {
        List<Attribute> attribute = repository.findAll();
        model.addAttribute("attribute", attribute);
        return "attribute/list";
    }

    @GetMapping("/new1")
    @ResponseBody
    public String listAlla(Model model) {
        return "ddd";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("attribute", new Attribute());

        List<AttributeCategoryMaster> attributeCategoryMasters = attributeCategoryMasterRepository.findAll();
        List<InputDataTypeMaster> inputDataTypeMasters = inputDataTypeMasterRepository.findAll();

//        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
        AttributeDTO attributeDTO = new AttributeDTO();
        model.addAttribute("attributeCategoryMasters", attributeCategoryMasters);
        model.addAttribute("inputDataTypeMasters", inputDataTypeMasters);
        model.addAttribute("attributeDTO", attributeDTO);
        return "attribute/form";
    }

    @PostMapping
    public String create(@ModelAttribute Attribute attribute) {
        repository.save(attribute);
        return "redirect:/attribute";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        List<AttributeCategoryMaster> attributeCategoryMasters = attributeCategoryMasterRepository.findAll();
        List<InputDataTypeMaster> inputDataTypeMasters = inputDataTypeMasterRepository.findAll();
        Attribute attribute = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        AttributeDTO attributeDTO = new AttributeDTO();
        SelectedOption selectedOption = new SelectedOption();

        if (id != null) {
            selectedOption.setSelectedOptionId(id);
            attributeDTO.setSelectedOption(selectedOption);
            attributeDTO.setAttribute(attribute);
        }

        model.addAttribute("selectedOption", selectedOption);

        model.addAttribute("attributeCategoryMasters", attributeCategoryMasters);
        model.addAttribute("inputDataTypeMasters", inputDataTypeMasters);
        model.addAttribute("attributeDTO", attributeDTO);
        return "attribute/form";
    }

    @PostMapping("/{id}")
    public String updateById(@PathVariable Long id, @ModelAttribute Attribute attribute) {
        attribute.setAttributeId(id);
        repository.save(attribute);
        return "redirect:/attribute";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        Attribute attribute = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location type Id:" + id));
        repository.delete(attribute);
        return "redirect:/attribute";
    }
}

