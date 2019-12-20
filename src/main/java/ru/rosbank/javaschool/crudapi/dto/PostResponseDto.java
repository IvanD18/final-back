package ru.rosbank.javaschool.crudapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rosbank.javaschool.crudapi.entity.PostEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Post response model")
public class PostResponseDto {
  @ApiModelProperty(value = "post id", position = 1)
  private int id;
  @ApiModelProperty(value = "post content", position = 2)
  private String content;
  @ApiModelProperty(position = 3)
  private String media;
  @ApiModelProperty(position = 4)
  private int likes;
}
