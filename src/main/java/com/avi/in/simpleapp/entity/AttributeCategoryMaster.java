package com.avi.in.simpleapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Slf4j
@EntityListeners(AuditingEntityListener.class)
public class AttributeCategoryMaster {
    @Id
    @Column(name = "attribute_category_master_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeCategoryMasterId;
    private String attributeCategoryMasterName;
    private String lastModifiedUser = "test";
    private String remarks = "test";


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTimestamp;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateTimestamp;

}
