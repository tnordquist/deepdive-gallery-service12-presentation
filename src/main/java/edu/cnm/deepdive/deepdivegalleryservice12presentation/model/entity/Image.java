package edu.cnm.deepdive.deepdivegalleryservice12presentation.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
import org.springframework.hateoas.server.EntityLinks;
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
@Component
public class Image {

  private static EntityLinks entityLinks;
  @NonNull
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

  @NonNull
  @Column(nullable = false, updatable = false)
  private String path;

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

  @NonNull
  public String getPath() {
    return path;
  }

  public void setPath(@NonNull String path) {
    this.path = path;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

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

  @NonNull
  public User getContributor() {
    return contributor;
  }

  public Gallery getGallery() {
    return gallery;
  }

  // TODO When is best time to add Hateaos for entity links?
 /* @Override
  public int hashCode() {
    return (id == null) ? 0 : id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return Objects.equals(this.id, ((Image) obj).id);
  }

  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(Image.class, id).toUri() : null;
  }

    @PostConstruct
  private void initHateoas() {
    //noinspection ResultOfMethodCallIgnored
    entityLinks.toString();
  }
  @Autowired
  public static void setEntityLinks(EntityLinks entityLinks) {
    Image.entityLinks = entityLinks;
  }*/
}
