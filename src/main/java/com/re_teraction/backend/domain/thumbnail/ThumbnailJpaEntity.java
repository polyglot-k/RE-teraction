package com.re_teraction.backend.domain.thumbnail;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String directory;

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
