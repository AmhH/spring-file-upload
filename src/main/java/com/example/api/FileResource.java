package com.example.api;


import com.example.dto.FileCreationDto;
import com.example.model.File;
import com.example.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author mb
 */
@RestController
@RequestMapping("/api/files")
@Api(tags = "File")
@AllArgsConstructor
@Slf4j
public class FileResource {

  private final FileService fileService;
  private final RequestMappingHandlerMapping handlerMapping;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ApiOperation(value = "Saves File instance.", tags = {"File"})
  @ApiResponses({
      @ApiResponse(code = 201, message = "A File was successfully created.")
  })
  public ResponseEntity create(@RequestPart(name = "model") @Valid final FileCreationDto model, @RequestPart(name = "file") MultipartFile file) {
    try {
      model.setData(file.getBytes());
    } catch (IOException e) {
      return ResponseEntity.badRequest().build();
    }

    final File fileCreated = fileService.create(model, file.getOriginalFilename());
    return ResponseEntity.status(HttpStatus.CREATED).body(fileCreated);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Reads a File instance.", tags = {"File"})
  @ApiResponses({
      @ApiResponse(code = 200, message = "File found."),
      @ApiResponse(code = 404, message = "File not found.")
  })
  public ResponseEntity read(@PathVariable("id") final String id) {
    final Optional<File> fileOpt = fileService.findById(id);
    if (fileOpt.isPresent()) {
      return ResponseEntity.ok(fileOpt.get());
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/download")
  @ApiOperation(value = "Downloads a File instance.", tags = {"File"})
  @ApiResponses({
      @ApiResponse(code = 200, message = "File downloaded."),
      @ApiResponse(code = 404, message = "File not found.")
  })
  public ResponseEntity<byte[]> download(@PathVariable("id") final String id) {
    final Optional<File> fileOpt = fileService.findById(id);
    if (fileOpt.isPresent()) {
      final File file = fileOpt.get();
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "." + file.getType() + "\"")
          .body(file.getData());
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Deletes a File instance.", tags = {"File"})
  @ApiResponses({
      @ApiResponse(code = 200, message = "A File was successfully deleted.")
  })
  public ResponseEntity delete(@PathVariable("id") final String id) {
    fileService.delete(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/endpoints")
  public ResponseEntity<List<String>> getAllEndPoints(){
    return ResponseEntity.ok(handlerMapping.getHandlerMethods()
        //.entrySet()
        .keySet()
        .stream()
        /*.collect(Collectors.toMap(entry -> entry.getKey().toString(),
            entry -> entry.getValue().getReturnType().())));*/
        .map(RequestMappingInfo::toString)
        .collect(Collectors.toList()));
  }
}

