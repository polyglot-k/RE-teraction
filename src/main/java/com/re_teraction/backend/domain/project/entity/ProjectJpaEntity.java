package com.re_teraction.backend.domain.project.entity;

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
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long id;


    @Column(name = "project_title", nullable = false)
    private String title;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "thumbnail_id", nullable = false)
    private Long thumbnailId;


    @Builder
    public ProjectJpaEntity(Long thumbnailId, String title,
            Long userId) {
        this.thumbnailId = thumbnailId;
        this.title = title;
        this.userId = userId;
    }

    public static ProjectJpaEntity of(Long thumbnailId, String title, Long userId) {
        return new ProjectJpaEntity(thumbnailId, title, userId);
    }
}
