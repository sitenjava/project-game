package com.game;

import com.game.data.entities.Role;
import com.game.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        RoleRepository roleRepository = context.getBean(RoleRepository.class);

        Role role1 = new Role(1, "ADMIN");
        Role role2 = new Role(2, "USER");

        try {
            roleRepository.save(role1);
            roleRepository.save(role2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
