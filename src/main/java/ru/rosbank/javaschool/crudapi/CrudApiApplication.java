package ru.rosbank.javaschool.crudapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.rosbank.javaschool.crudapi.entity.PostEntity;
import ru.rosbank.javaschool.crudapi.repository.PostRepository;
import ru.rosbank.javaschool.crudapi.service.UserService;

import java.util.List;

@SpringBootApplication
public class CrudApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudApiApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(PostRepository repository, UserService service) {
    return args -> {
      final PostEntity first = (new PostEntity(0, "First", null, false, 0));
      final PostEntity second = new PostEntity(0, "Second", null, false, 0);
      final PostEntity third = (new PostEntity(0, "Third", null, false, 0));
      repository.saveAll(List.of(
              first,
              second,
              third
      ));

      service.create("Vasya", "login", "password",
          List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
      );
      service.create("Petya", "qwer", "1234",
          List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"))
      );
    };
  }

}
