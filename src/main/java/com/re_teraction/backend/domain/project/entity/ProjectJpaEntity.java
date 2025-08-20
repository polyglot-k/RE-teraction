package com.re_teraction.backend.domain.project.entity;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", nullable = false)
    private Long id;

    @Column(name = "project_thumbnail_url", nullable = false)
    public String thumbnailUrl;

    @Column(name = "project_title", nullable = false)
    public String title;

    @ElementCollection(targetClass = ProjectCategory.class)
    @CollectionTable(
            name = "project_categories",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Set<ProjectCategory> categories = new HashSet<>();

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Builder
    public ProjectJpaEntity(String thumbnailUrl, String title, Set<ProjectCategory> categories, Long userId) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.categories = categories;
        this.userId = userId;
    }
    public static ProjectJpaEntity of(String thumbnailUrl, String title, Set<ProjectCategory> categories, Long userId) {
        return new ProjectJpaEntity(thumbnailUrl, title, categories, userId);
    }
}
