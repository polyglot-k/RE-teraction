package com.re_teraction.backend.domain.paper;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paper_id", nullable = false)
    private Long id;

    @Column(name = "paper_thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(name = "paper_title", nullable = false)
    private String title;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public PaperJpaEntity(String thumbnailUrl, String title, Long userId) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.userId = userId;
    }

    public static PaperJpaEntity of(String thumbnailUrl, String title, Long userId) {
        return PaperJpaEntity.builder()
                .thumbnailUrl(thumbnailUrl)
                .title(title)
                .userId(userId)
                .build();
    }
}
