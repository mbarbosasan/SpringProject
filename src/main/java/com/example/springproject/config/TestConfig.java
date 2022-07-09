package com.example.springproject.config;

import com.example.springproject.Entities.User;
import com.example.springproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Brown", "maria@mail.com", "999999", "1234567");
        User user2 = new User(null, "Alex Green", "alex@mail.com", "888888", "123456");

        userRepository.saveAll(Arrays.asList(user1, user2));
    }
}
