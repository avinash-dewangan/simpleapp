package com.avi.in.simpleapp.dao.impl;

import com.avi.in.simpleapp.dao.IUserDao;
import com.avi.in.simpleapp.entity.Users;
import com.avi.in.simpleapp.exception.GenericException;
import com.avi.in.simpleapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Users save(Users user) throws GenericException {
        return userRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
