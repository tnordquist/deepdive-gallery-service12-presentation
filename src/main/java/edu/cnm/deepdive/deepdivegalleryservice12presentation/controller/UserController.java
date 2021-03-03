package edu.cnm.deepdive.deepdivegalleryservice12presentation.controller;

import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao.UserRepository;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.User;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User me(Authentication auth) {
    return (User) auth.getPrincipal();
  }

}