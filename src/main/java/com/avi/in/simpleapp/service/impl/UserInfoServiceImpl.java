package com.avi.in.simpleapp.service.impl;


import com.avi.in.simpleapp.entity.UserInfo;
import com.avi.in.simpleapp.exception.DatabaseException;
import com.avi.in.simpleapp.repository.UserInfoRepository;
import com.avi.in.simpleapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo save(UserInfo userInfo) {
        try {
            return userInfoRepository.save(userInfo);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to save user info", ex);
        }
    }

    @Override
    public List<UserInfo> findAll() {
        try {
            return userInfoRepository.findAll();
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to retrieve user info",ex);
        }
    }

    @Override
    public UserInfo findById(Long id) {
        return null;
    }


    @Override
    public void deleteById(Long id) {
        try {
            userInfoRepository.deleteById(id);
        } catch (DataAccessException ex) {
            throw new DatabaseException("Failed to delete user info by id", ex);
        }
    }
}