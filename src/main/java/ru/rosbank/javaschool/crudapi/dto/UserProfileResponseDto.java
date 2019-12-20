package ru.rosbank.javaschool.crudapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.rosbank.javaschool.crudapi.entity.PostEntity;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
// для фронта
public class UserProfileResponseDto {
  private long id;
  private String name;
  private String username;
  private Collection<GrantedAuthority> authorities;
}
