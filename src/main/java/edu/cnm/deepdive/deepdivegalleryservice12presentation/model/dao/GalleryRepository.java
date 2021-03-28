package edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao;

import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends CrudRepository<Gallery, UUID> {

  Iterable<Gallery> getAllByOrderByTitleAsc();

  /**
   * This query finds an Event by the user that posted it and the event id.
   * @param id is the primary key for event.
   * @param creator is a User object.
   * @return An event associated with the user that created the event.
   */
  Optional<Gallery> findByIdAndCreator(UUID id, User creator);
}
