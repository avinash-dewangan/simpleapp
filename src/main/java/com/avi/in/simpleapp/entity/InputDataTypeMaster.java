package com.avi.in.simpleapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InputDataTypeMaster {
    @Id
    @Column(name = "input_data_type_master_id", nullable = false)
    private Long inputDataTypeMasterId;
    private String inputDataTypeMasterName;

}
