package edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao;


import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findFirstByOauthKey(String oauthKey);

}
