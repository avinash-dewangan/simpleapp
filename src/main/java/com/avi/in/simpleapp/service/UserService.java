package com.avi.in.simpleapp.service;




import com.avi.in.simpleapp.entity.User;
import com.avi.in.simpleapp.exception.GenericException;

import java.util.List;

public interface UserService {
    User save(User user) throws GenericException;
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
