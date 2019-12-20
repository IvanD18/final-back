package ru.rosbank.javaschool.crudapi.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.rosbank.javaschool.crudapi.dto.PostSaveRequestDto;
import ru.rosbank.javaschool.crudapi.dto.PostResponseDto;
import ru.rosbank.javaschool.crudapi.entity.UserEntity;
import ru.rosbank.javaschool.crudapi.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController // ко всем методам будет дописано @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class RestPostController {
  private final PostService service;
  private final Logger logger = LoggerFactory.getLogger(RestPostController.class);

  // @ResponseBody
  // AutoConfiguration -> Jackson
  // HttpMessageConverters -> RequestResponse...
  @GetMapping // @RequestMapping(method = GET) -> GET /api/posts
  @ApiOperation(value = "Get all posts", notes = "Get all posts long description")
  public List<PostResponseDto> getAll() {
    logger.info(Thread.currentThread().getName());
    return service.getAll();
  }

//  @GetMapping(value = "/search", params = "q") // фильтрация по наличию параметра
  @GetMapping("/search/{q}")
public List<PostResponseDto> searchByContent(@ApiParam("Text to search by") @PathVariable String q) {
    return service.searchByContent(q);
  }

  // -> x-www-urlencoded...
  // -> multipart/form-data
  // Content-Type: MIME тип
  // POST -> create/update
  @PostMapping // DataBinding
  @PreAuthorize("hasRole('ADMIN') || #dto.id == 0 || @permissionService.isPostUpdateAvailable(#dto.id, #entity)")
  public PostResponseDto save(@Valid @RequestBody PostSaveRequestDto dto, @AuthenticationPrincipal UserEntity entity) {
    return service.save(dto);
  }

  // DELETE /api/posts/:id -> ?itemId=10 -> req.getParameter()
  @DeleteMapping("/{id}")
// public void removeById(@PathVariable("id") int id)
// if param name = path variable name, то дополнительно ничего не нужно
  @PreAuthorize("@permissionService.isPostRemoveAvailable(#id, #entity)")
  public void removeById(@PathVariable int id, @AuthenticationPrincipal UserEntity entity) {
//    throw new BadRequestException("bad.request");
    service.removeById(id);
  }

  @PostMapping("/{id}/likes")
  public PostResponseDto likeById(@PathVariable int id) {
    return service.likeById(id);
  }

  @DeleteMapping("/{id}/likes")
  public PostResponseDto dislikeById(@PathVariable int id) {
    return service.dislikeById(id);
  }
}
