package com.avi.in.simpleapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private LocalDate registrationDate;

    @ManyToOne
    private Course course;

}
