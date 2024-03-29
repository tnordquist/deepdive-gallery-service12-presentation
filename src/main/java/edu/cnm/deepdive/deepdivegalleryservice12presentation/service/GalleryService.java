package edu.cnm.deepdive.deepdivegalleryservice12presentation.service;

import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.dao.GalleryRepository;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

  private final GalleryRepository galleryRepository;

  public GalleryService(
      GalleryRepository galleryRepository) {
    this.galleryRepository = galleryRepository;
  }

  public Gallery newGallery(Gallery gallery, User creator) {
    gallery.setCreator(creator);
    return galleryRepository.save(gallery);
  }

  public Gallery save(@NonNull Gallery gallery) {
    return galleryRepository.save(gallery);
  }

  /**
   * This method returns a gallery by passing in the associated gallery id.
   * @param id the gallery id
   * @return returns an Optional that may or may not wrap a gallery.
   */
  public Optional<Gallery> get(UUID id) {
    return galleryRepository.findById(id);
  }

  /**
   * This method returns a gallery by passing in the User who created it and the associated gallery id.
   * @param id this is the gallery primary key.
   * @param creator this is the current signed in User of the application
   * @return an Event object, if there are any associated with the User.
   */

  public Optional<Gallery> get(UUID id, User creator) {
    return galleryRepository.findByIdAndCreator(id,creator);
  }


  public Iterable<Gallery> getAll() {
    return galleryRepository.getAllByOrderByTitleAsc();
  }
}