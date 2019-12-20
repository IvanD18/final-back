package ru.rosbank.javaschool.crudapi.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosbank.javaschool.crudapi.dto.PostResponseDto;
import ru.rosbank.javaschool.crudapi.dto.PostSaveRequestDto;
import ru.rosbank.javaschool.crudapi.entity.PostEntity;
import ru.rosbank.javaschool.crudapi.exception.BadRequestException;
import ru.rosbank.javaschool.crudapi.mapper.PostMapper;
import ru.rosbank.javaschool.crudapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
  private final PostRepository repository;
  private final PostMapper mapper;
  private final Logger logger = LoggerFactory.getLogger(PostService.class);

  public List<PostResponseDto> getAll() {
    return repository.findAll().stream()
        .map(mapper::entityToPostResponseDto)
        .collect(Collectors.toList());
  }


  public PostResponseDto save(PostSaveRequestDto dto) {
    return mapper.entityToPostResponseDto(repository.save(mapper.dtoToPostEntity(dto)));
  }

  public void removeById(int id) {
    repository.deleteById(id);
  }

  public List<PostResponseDto> searchByContent(String q) {
    return repository.findAllByContentLike(q).stream()
        .map(mapper::entityToPostResponseDto)
        .collect(Collectors.toList());
  }

  public PostResponseDto likeById(int id) {
    repository.increaseLikesById(id, 1);
    final PostEntity entity = repository.findById(id)
        .orElseThrow(BadRequestException::new);
    return mapper.entityToPostResponseDto(entity);
  }

  public PostResponseDto dislikeById(int id) {
    repository.increaseLikesById(id, -1);
    final PostEntity entity = repository.findById(id)
        .orElseThrow(BadRequestException::new);
    return mapper.entityToPostResponseDto(entity);
  }
}
