package ru.rosbank.javaschool.crudapi.service;

import org.springframework.stereotype.Service;
import ru.rosbank.javaschool.crudapi.entity.UserEntity;

@Service
public class PermissionService {
  public boolean isPostRemoveAvailable(int postId, UserEntity entity) {
    // FIXME: сделать нормально
    // Post -> author
    return postId == entity.getId();
  }

  public boolean isPostUpdateAvailable(int postId, UserEntity entity) {
    return postId == entity.getId();
  }
}
