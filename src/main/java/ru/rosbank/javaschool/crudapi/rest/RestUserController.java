package ru.rosbank.javaschool.crudapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.rosbank.javaschool.crudapi.dto.UserProfileResponseDto;
import ru.rosbank.javaschool.crudapi.dto.UserResponseDto;
import ru.rosbank.javaschool.crudapi.entity.PostEntity;
import ru.rosbank.javaschool.crudapi.entity.UserEntity;
import ru.rosbank.javaschool.crudapi.exception.UnauthorizedException;
import ru.rosbank.javaschool.crudapi.mapper.UserMapper;
import ru.rosbank.javaschool.crudapi.service.UserService;

import javax.ws.rs.Path;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RestUserController {
  private final UserService service;
  private final UserMapper mapper;
  @GetMapping("/me")
  public  UserProfileResponseDto me(@AuthenticationPrincipal UserEntity entity)  {
    return mapper.entityToUserProfileResponseDto(entity);
  }
  @PostMapping("/registration")
  public void registration(@RequestBody UserEntity user){
    System.out.println(user.getName());
    System.out.println(user.getUsername());
    System.out.println(user.getPassword());
    service.create(user.getName(),user.getUsername(),user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
  }

}
