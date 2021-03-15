package edu.cnm.deepdive.deepdivegalleryservice12presentation.controller;

import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao.GalleryRepository;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao.UserRepository;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.User;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.service.GalleryService;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.service.UserService;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/galleries")
//@ExposesResourceFor(Gallery.class)
public class GalleryController {

  private final UserService userService;
  private final UserRepository userRepository;
  private final GalleryService galleryService;
  private final GalleryRepository galleryRepository;

  public GalleryController(UserService userService,
      UserRepository userRepository,
      GalleryService galleryService,
      GalleryRepository galleryRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
    this.galleryService = galleryService;
    this.galleryRepository = galleryRepository;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Gallery post(@RequestBody Gallery gallery, Authentication auth) {
    return galleryService.newGallery(gallery, (User) auth.getPrincipal());
  }
}