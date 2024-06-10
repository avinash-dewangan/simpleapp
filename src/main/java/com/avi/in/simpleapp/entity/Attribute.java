package com.avi.in.simpleapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Attribute {
    @Id
    @Column(name = "attribute_id", nullable = false)
    private Long attributeId;
    private String attributeName;
    @ManyToOne
    @JoinColumn(name = "attribute_category_master_id")
    private AttributeCategoryMaster attributeCategoryMasterId;

    @ManyToOne
    @JoinColumn(name = "input_data_type_master_id")
    private InputDataTypeMaster inputDataTypeMasterId;




}
