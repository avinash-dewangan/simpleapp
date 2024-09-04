package com.avi.in.simpleapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InputTypeMaster {
    @Id
    @Column(name = "id", nullable = false)
    private Long inputTypeId;

    private String inputTypeName;


}
