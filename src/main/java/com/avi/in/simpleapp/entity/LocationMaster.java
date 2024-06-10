package com.avi.in.simpleapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LocationMaster {
    @Id
    @Column(name = "location_master_id", nullable = false)
    private Long LocationMasterId;
    private long locationTypeMasterId;
    private long locationMasterParentId;
    private String locationMasterName;
    private String locationMasterNameLocalLang;
    private String locationMasterLGDCode;
}
