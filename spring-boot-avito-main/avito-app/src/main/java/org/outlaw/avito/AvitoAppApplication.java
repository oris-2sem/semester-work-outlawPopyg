package org.outlaw.avito;

import org.outlaw.avito.models.User;
import org.outlaw.avito.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class AvitoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvitoAppApplication.class, args);
    }

}
