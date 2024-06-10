package com.avi.in.simpleapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Metadata {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String name;
    private String nameHI;
    private String mobile;
    private String address;


}
