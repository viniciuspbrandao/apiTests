package com.vb.apiTests.config;

import com.vb.apiTests.domain.Users;
import com.vb.apiTests.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

//    @Bean
//    public void startDB() {
//        Users user1 = new Users(null, "vinicius", "mail","deere");
//        Users user2 = new Users(null, "vini", "mail","dere");
//
//        repository.saveAll(List.of(user1,user2));
//    }

}
