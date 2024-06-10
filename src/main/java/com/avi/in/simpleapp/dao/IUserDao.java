package com.avi.in.simpleapp.dao;

import com.avi.in.simpleapp.entity.User;
import com.avi.in.simpleapp.entity.UserInfo;
import com.avi.in.simpleapp.exception.GenericException;

import java.util.List;

public interface IUserDao {
    User save(User user) throws GenericException;
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
