package com.avi.in.simpleapp.dto;

import com.avi.in.simpleapp.entity.Attribute;

public class AttributeDTO {
    Attribute attribute;
    SelectedOption selectedOption;

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public SelectedOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(SelectedOption selectedOption) {
        this.selectedOption = selectedOption;
    }
}
