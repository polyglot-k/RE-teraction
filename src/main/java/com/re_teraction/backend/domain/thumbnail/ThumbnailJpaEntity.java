package com.re_teraction.backend.domain.thumbnail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "thumbnails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ThumbnailJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbnail_id", nullable = false)
    private Long id;

    @Column(name = "directory", nullable = false)
    private String directory;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Builder
    public ThumbnailJpaEntity(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
    }

    public static ThumbnailJpaEntity create(String directory, String filename) {
        return ThumbnailJpaEntity.builder()
                .directory(directory)
                .filename(filename)
                .build();
    }
}
