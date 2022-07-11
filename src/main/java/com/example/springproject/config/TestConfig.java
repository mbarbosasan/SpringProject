package com.example.springproject.config;

import com.example.springproject.Entities.Enums.OrderStatus;
import com.example.springproject.Entities.Order;
import com.example.springproject.Entities.User;
import com.example.springproject.Repositories.OrderRepository;
import com.example.springproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Brown", "maria@mail.com", "999999", "1234567");
        User user2 = new User(null, "Alex Green", "alex@mail.com", "888888", "123456");
        Order o1 = new Order(null, Instant.parse("2022-07-07T19:00:00Z"), OrderStatus.PAID, user1);
        Order o2 = new Order(null, Instant.parse("2022-07-06T15:00:00Z"), OrderStatus.DELIVERED, user2);
        Order o3 = new Order(null, Instant.parse("2022-07-05T12:00:00Z"),OrderStatus.DELIVERED, user1);
        Order o4 = new Order(null, Instant.parse("2022-07-06T08:00:00Z"), OrderStatus.WAITING_PAYMENT, user2);

        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
    }
}
