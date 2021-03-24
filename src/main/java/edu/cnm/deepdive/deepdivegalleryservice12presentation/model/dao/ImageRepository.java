package edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao;


import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Image;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, UUID> {

  Iterable<Image> getAllByOrderByCreatedDesc();
}
