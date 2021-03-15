package edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao;

import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Gallery;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, UUID> {

}