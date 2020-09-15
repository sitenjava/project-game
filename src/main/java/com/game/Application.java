package com.game;

import com.game.data.entities.Action;
import com.game.data.entities.Redirection;
import com.game.data.entities.Role;
import com.game.data.entities.Url;
import com.game.data.repository.ActionRepository;
import com.game.data.repository.RedirectionRepository;
import com.game.data.repository.RoleRepository;
import com.game.data.repository.UrlRepository;
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

//        initDatabase(context);
    }

    private static void initDatabase(ApplicationContext context) {
        RoleRepository roleRepository = context.getBean(RoleRepository.class);
        ActionRepository actionRepository = context.getBean(ActionRepository.class);
        UrlRepository urlRepository = context.getBean(UrlRepository.class);
        RedirectionRepository redirectionRepository = context.getBean(RedirectionRepository.class);

        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");

        Action create = new Action("POST");
        Action read = new Action("GET");
        Action update = new Action("PUT");
        Action delete = new Action("DELETE");

        Url apiUser = new Url("/api/user");
        Url apiUserDetail = new Url("/api/user/{id}");
        Url login = new Url("/login");
        Url register = new Url("/register");
        Url home = new Url("/");

        Redirection addUser = new Redirection(roleAdmin, apiUser, create);
        Redirection getUsers = new Redirection(roleAdmin, apiUser, read);
        Redirection getUserDetail = new Redirection(roleAdmin, apiUserDetail, read);
        Redirection updateUserDetail = new Redirection(roleAdmin, apiUserDetail, update);
        Redirection loginPost = new Redirection(roleAdmin, login, create);
        Redirection loginRead = new Redirection(roleAdmin, login, read);


        try {
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            actionRepository.save(create);
            actionRepository.save(read);
            actionRepository.save(update);
            actionRepository.save(delete);
            urlRepository.save(apiUser);
            urlRepository.save(apiUserDetail);
            redirectionRepository.save(addUser);
            redirectionRepository.save(getUsers);
            redirectionRepository.save(getUserDetail);
            redirectionRepository.save(updateUserDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
