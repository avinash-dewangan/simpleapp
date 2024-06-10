package com.avi.in.simpleapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LocationTypeMaster {
    @Id
    @Column(name = "location_type_master_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationTypeMasterId;
    private String locationTypeMasterName;

}
