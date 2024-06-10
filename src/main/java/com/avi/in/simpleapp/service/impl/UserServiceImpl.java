package com.avi.in.simpleapp.service.impl;


import com.avi.in.simpleapp.dao.IUserDao;
import com.avi.in.simpleapp.entity.User;
import com.avi.in.simpleapp.exception.DatabaseException;
import com.avi.in.simpleapp.exception.GenericException;
import com.avi.in.simpleapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao iUserDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User save(User user) throws GenericException {
        try {
            // Check if user with the same username or email already exists
            if (iUserDao.findByUsername(user.getUsername()) != null) {
                throw new DataIntegrityViolationException("Username already exists");
            }
            if (iUserDao.findByEmail(user.getEmail()) != null) {
                throw new DataIntegrityViolationException("Email already exists");
            }
            return iUserDao.save(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new GenericException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return iUserDao.findAll();
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to retrieve users", ex);
        }
    }

    @Override
    public User findById(Long id) {
        try {
            return iUserDao.findById(id);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to find user by id", ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            iUserDao.deleteById(id);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to delete user by id", ex);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return iUserDao.findByUsername(username);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to find user by username", ex);
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return iUserDao.findByEmail(email);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to find user by email", ex);
        }
    }
}
