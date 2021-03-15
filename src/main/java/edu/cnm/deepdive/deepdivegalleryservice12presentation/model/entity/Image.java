package edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    indexes = {
        @Index(columnList = "created"),
        @Index(columnList = "title")
    }
)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(
    value = {"id", "created", "contributor"},
    allowGetters = true, ignoreUnknown = true
)
@JsonPropertyOrder({"id", "title", "description", "href", "created", "contributor", "name",
    "description"})
@Component
public class Image /*implements Comparable<Image>, FlatImage*/ {
  //TODO when should Comparable be added and Comparator be added?

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "image_id", nullable = false, updatable = false, columnDefinition = "CHAR(16) FOR BIT DATA")
  private UUID id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  @Column(length = 100)
  private String title;

// TODO should Path be created for this project?

  @NonNull
  @Column(nullable = false, updatable = false)
  private String name;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String contentType;

  @Column(length = 1024)
  private String description;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "contributor_id", nullable = false, updatable = false)
//  @JsonSerialize(as = FlatUser.class)
  private User contributor;


  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "gallery_id", nullable = false, updatable = false)
//  @JsonSerialize(as = FlatGallery.class)
  private Gallery gallery;

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  // TODO Consider rather Path is needed, or only name

  /**
   * Returns the original filename of this image.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets the original filename of this image to the specified {@code name}.
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the MIME type of this image.
   */
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the {@link User} that contributed this image.
   */
  @NonNull
  public User getContributor() {
    return contributor;
  }

  /**
   * Sets this image's contributor to the specified {@link User}.
   */
  public void setContributor(@NonNull User contributor) {
    this.contributor = contributor;
  }

  public Gallery getGallery() {
    return gallery;
  }

  public void setGallery(Gallery gallery) {
    this.gallery = gallery;
  }

  // TODO When to implement Hateaos?
  // TODO What are hash, equal, compare used for here?

}