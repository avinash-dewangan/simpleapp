package com.avi.in.simpleapp.service;



import com.avi.in.simpleapp.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo save(UserInfo userInfo);
    List<UserInfo> findAll();
    UserInfo findById(Long id);
    void deleteById(Long id);
}
