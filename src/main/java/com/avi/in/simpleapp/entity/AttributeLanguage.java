package com.avi.in.simpleapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class AttributeLanguage {
    @Id
    @Column(name = "attribute_language_id", nullable = false)
    private Long attributeLanguageId;
    private String attributeNameHi;
    private String attributeNameEn;
    private long attributeId;


}
