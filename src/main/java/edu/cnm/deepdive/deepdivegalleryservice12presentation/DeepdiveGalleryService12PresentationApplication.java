package edu.cnm.deepdive.deepdivegalleryservice12presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@SpringBootApplication
@EnableHypermediaSupport(type = {HypermediaType.HAL})
public class DeepdiveGalleryService12PresentationApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeepdiveGalleryService12PresentationApplication.class, args);
  }

}
