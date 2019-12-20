package ru.rosbank.javaschool.crudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rosbank.javaschool.crudapi.constraint.LoginValidator;
import ru.rosbank.javaschool.crudapi.constraint.NameValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NameValidator
  private String name;
  @Column(nullable = false, unique = true)
  @LoginValidator
  private String username;
  @Column(nullable = false)
  @NotNull
  @Size(min=3)
  private String password;
  @ElementCollection(fetch = FetchType.EAGER)
  Collection<GrantedAuthority> authorities;
  boolean accountNonExpired;
  boolean accountNonLocked;
  boolean credentialsNonExpired;
  boolean enabled;

  public void setUsername(String username) {
    this.username = username;
  }
}
