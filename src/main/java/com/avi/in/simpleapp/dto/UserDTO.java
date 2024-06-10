package com.avi.in.simpleapp.dto;

import jakarta.persistence.Column;

public class UserDTO {
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
