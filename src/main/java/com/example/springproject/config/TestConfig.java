package com.example.springproject.config;

import com.example.springproject.Entities.*;
import com.example.springproject.Entities.Enums.OrderStatus;
import com.example.springproject.Repositories.*;
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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Ebook");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The lord of the Rings", "Lorem Ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus, maecenas ante.", 2190.0, "");
        Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0,"");
        Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(null, "Mails for Dummies", "Cras fringilla convallis sem vel faucibus. ", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product2.getCategories().add(category3);
        product3.getCategories().add(category3);
        product4.getCategories().add(category3);
        product5.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        User user1 = new User(null, "Maria Brown", "maria@mail.com", "999999", "1234567");
        User user2 = new User(null, "Alex Green", "alex@mail.com", "888888", "123456");

        Order o1 = new Order(null, Instant.parse("2022-07-07T19:00:00Z"), OrderStatus.PAID, user1);
        Order o2 = new Order(null, Instant.parse("2022-07-06T15:00:00Z"), OrderStatus.DELIVERED, user2);
        Order o3 = new Order(null, Instant.parse("2022-07-05T12:00:00Z"),OrderStatus.DELIVERED, user1);
        Order o4 = new Order(null, Instant.parse("2022-07-06T08:00:00Z"), OrderStatus.WAITING_PAYMENT, user2);

        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));

        OrderItem receipt1 = new OrderItem(o1, product1, 2, product1.getPrice());
        OrderItem receipt2 = new OrderItem(o1, product3, 1, product3.getPrice());
        OrderItem receipt3 = new OrderItem(o2, product3, 2, product3.getPrice());
        OrderItem receipt4 = new OrderItem(o3, product5, 2, product5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(receipt1, receipt2, receipt3, receipt4));
    }
}
