package com.avi.in.simpleapp.service;




import com.avi.in.simpleapp.entity.Users;
import com.avi.in.simpleapp.exception.GenericException;

import java.util.List;

public interface UserService {
    Users save(Users user) throws GenericException;
    List<Users> findAll();
    Users findById(Long id);
    void deleteById(Long id);
    Users findByUsername(String username);
    Users findByEmail(String email);
}
