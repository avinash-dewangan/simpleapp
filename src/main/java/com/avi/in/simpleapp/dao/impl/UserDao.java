package com.avi.in.simpleapp.dao.impl;

import com.avi.in.simpleapp.dao.IUserDao;
import com.avi.in.simpleapp.entity.User;
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
    public User save(User user) throws GenericException {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
