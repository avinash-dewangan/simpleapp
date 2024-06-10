package com.avi.in.simpleapp.controller;


import com.avi.in.simpleapp.entity.UserInfo;
import com.avi.in.simpleapp.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo savedUserInfo = userInfoService.save(userInfo);
        return ResponseEntity.ok(savedUserInfo);
    }

    @GetMapping
    public ResponseEntity<List<UserInfo>> getAllUserInfos() {
        List<UserInfo> userInfos = userInfoService.findAll();
        return ResponseEntity.ok(userInfos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoById(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.findById(id);
        if (userInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long id) {
        userInfoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
