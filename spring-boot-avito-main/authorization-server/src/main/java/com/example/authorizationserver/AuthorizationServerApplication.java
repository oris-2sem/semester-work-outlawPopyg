package com.example.authorizationserver;

import com.example.authorizationserver.details.Role;
import com.example.authorizationserver.details.User;
import com.example.authorizationserver.repositories.RoleRepository;
import com.example.authorizationserver.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

}
