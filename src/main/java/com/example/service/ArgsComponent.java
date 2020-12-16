package com.example.service;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ArgsComponent {

  @Autowired
  public ArgsComponent(ApplicationArguments args){
    log.info("Source Args {}",Arrays.toString(args.getSourceArgs()));
    log.info("Non Option Args {}",args.getNonOptionArgs());
    log.info("Option Names {}", args.getOptionNames());
    log.info("Option Names {}", args.getOptionValues("n"));
  }

}
