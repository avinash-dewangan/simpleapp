package com.avi.in.simpleapp.service;

import com.avi.in.simpleapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public void run(String... args) {
//        List<Users> locationTypeMasters = Arrays.asList(
//                new Users("avinash","avi@gmail1.com","password"),
//                new Users("avinash1","avi@gmail1.com","password"),
//                new Users("avinash2","avi@gmail2.com","password")
//        );
//        userRepository.saveAll(locationTypeMasters);
    }
}
