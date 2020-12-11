package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Validated
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class FileCreationDto {

  @NotBlank
  private String username;

  private String name;
  private String description;

  @JsonIgnore
  @Setter
  private byte[] data;
}