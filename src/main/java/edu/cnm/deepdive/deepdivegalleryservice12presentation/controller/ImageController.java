package edu.cnm.deepdive.deepdivegalleryservice12presentation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Gallery;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.Image;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity.User;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.service.GalleryService;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.service.ImageService;
import edu.cnm.deepdive.deepdivegalleryservice12presentation.view.ImageViews;
import java.io.IOException;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@ExposesResourceFor(Image.class)
public class ImageController {

  private final ImageService imageService;
  private final GalleryService galleryService;

  private static final String ATTACHMENT_DISPOSITION_FORMAT = "attachment: filename=\"%s\"";

  public ImageController(ImageService imageService,
      GalleryService galleryService) {
    this.imageService = imageService;
    this.galleryService = galleryService;
  }

  /**
   * Stores uploaded file content along with a new {@link Image} instance referencing the content.
   *
   * @param title       Summary of uploaded content.
   * @param description Detailed description of uploaded content.
   * @param file        MIME content of single file upload.
   * @param auth        Authentication token with {@link User} principal.
   * @return Instance of {@link Image} created &amp; persisted for the uploaded content.
   */

/* @JsonView(ImageViews.Hierarchical.class)
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Image> post(
      @RequestParam MultipartFile file,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String description,
      Authentication auth) throws IOException, HttpMediaTypeException {

    Image image = imageService.store(file, title, description, (User) auth.getPrincipal());
    return ResponseEntity.created(image.getHref()).body(image);
  }*/


  /**
   * Stores uploaded file content along with a new {@link Image} instance referencing the content.
   *
   * @param title       Summary of uploaded content.
   * @param description Detailed description of uploaded content.
   * @param file        MIME content of single file upload.
   * @param auth        Authentication token with {@link User} principal.
   * @return Instance of {@link Image} contributed &amp; in association with a {@Link Gallery} persisted for the uploaded content.
   */
  @JsonView(ImageViews.Hierarchical.class)
  @PostMapping(value = "/{galleryId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Image> postByCreator(
      @PathVariable(required = false) UUID galleryId,
      @RequestParam MultipartFile file,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String description,
      Authentication auth) throws IOException, HttpMediaTypeException {
    return galleryService.get(galleryId, (User) auth.getPrincipal())
    .map((gallery) -> securePost(file,(User) auth.getPrincipal(), gallery, title,description))
        .orElseThrow(ImageNotFoundException::new);
  }

  /**
   * Selects and returns all images.
   *
   * @param auth Authentication token with {@link User} principal.
   * @return Selected images.
   */

  @JsonView(ImageViews.Flat.class)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Image> list(Authentication auth) {
    return imageService.list();
  }


  @JsonView(ImageViews.Hierarchical.class)
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Image get(@PathVariable UUID id, Authentication auth) {
    return imageService
        .get(id)
        .orElseThrow();
  }

  @GetMapping("/{id}/content")
  public ResponseEntity<Resource> getContent(@PathVariable UUID id, Authentication auth) {
    return imageService
        .get(id)
        .map((image) -> {
          try {
            Resource resource = imageService.getContent(image);
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                    String.format(ATTACHMENT_DISPOSITION_FORMAT, image.getName()))
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                .header(HttpHeaders.CONTENT_TYPE, image.getContentType())
                .body(resource);
          } catch (IOException e) {
            throw new RuntimeException(e); // FIXME
          }
        })
        .orElseThrow();
  }

  private ResponseEntity<Image> securePost(MultipartFile file, User user, Gallery gallery,
      String title, String description) {
    try {
      Image image = imageService.store(file, user, gallery, title, description);
      return ResponseEntity.created(image.getHref()).body(image);
    } catch (IOException e) {
      throw new StorageException(e);
    } catch (HttpMediaTypeException e) {
      throw new MimeTypeNotAllowedException();
    }
  }
}