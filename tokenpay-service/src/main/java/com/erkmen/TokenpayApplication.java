package com.erkmen;

import com.erkmen.domain.factory.ApplicationFactory;
import com.erkmen.domain.factory.DefaultApplicationFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TokenpayApplication implements CommandLineRunner {

//    @Autowired
//    private ProductService productService;

//    @Autowired
//    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(TokenpayApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public ApplicationFactory getApplicationFactory() {
        return new DefaultApplicationFactory();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

    @Override
    public void run(String... args) throws Exception {

//        Product prod1 = productService.getByProductCode("00001");
//        if (prod1 == null) {
//            Product product1 = new Product();
//            product1.setCode("00001");
//            product1.setName("Zeytinyağı");
//            product1.setDescription("Zeytinyağı");
//            product1.setQuantityType(QuantityType.LITRE);
//            product1.setPrice(50d);
//            product1.setIsActive(Boolean.TRUE);
//            productService.save(product1);
//        }

//        Product prod2 = productService.getByProductCode("00002");
//        if (prod2 == null) {
//            Product product2 = new Product();
//            product2.setCode("00002");
//            product2.setName("Sabun");
//            product2.setDescription("5'li sabun paketi");
//            product2.setQuantityType(QuantityType.PACKET);
//            product2.setPrice(50d);
//            product2.setIsActive(Boolean.TRUE);
//            productService.save(product2);
//        }

//        User us = userService.getByUsername("admin");
//        if (us == null) {
//            User user = new User();
//            user.setUsername("admin");
//            user.setEmail("admin@hotmail.com");
//            user.setRole(Role.ADMIN);
//            user.setPassword(passwordEncoder().encode("admin123"));
//            userService.save(user);
//        }

    }

}
