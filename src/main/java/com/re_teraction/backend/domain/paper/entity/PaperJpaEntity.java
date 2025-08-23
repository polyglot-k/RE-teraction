package com.re_teraction.backend.domain.paper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "papers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PaperJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id", nullable = false)
    private Long id;

    @Column(name = "paper_title", nullable = false)
    private String title;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "thumbnail_id", nullable = false)
    private Long thumbnailId;

    @Builder
    public PaperJpaEntity(Long thumbnailId, String title, Long userId) {
        this.thumbnailId = thumbnailId;
        this.title = title;
        this.userId = userId;
    }

    public static PaperJpaEntity of(Long thumbnailId, String title, Long userId) {
        return PaperJpaEntity.builder()
                .thumbnailId(thumbnailId)
                .title(title)
                .userId(userId)
                .build();
    }
}
