package com.avi.in.simpleapp.service;

import com.avi.in.simpleapp.entity.User;
import com.avi.in.simpleapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public void run(String... args) {
//        List<User> locationTypeMasters = Arrays.asList(
//                new User("avinash","avi@gmail1.com","password"),
//                new User("avinash1","avi@gmail1.com","password"),
//                new User("avinash2","avi@gmail2.com","password")
//        );
//        userRepository.saveAll(locationTypeMasters);
    }
}
