package com.avi.in.simpleapp.excelupload;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_upload_excel_data")
public class UserUploadDataExcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    // Getters and Setters
}
