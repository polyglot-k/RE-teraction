package com.re_teraction.backend.domain.project.entity;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "project_categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectCategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectCategory category;

    @Builder
    public ProjectCategoryJpaEntity(Long projectId, ProjectCategory category) {
        this.projectId = projectId;
        this.category = category;
    }

    public static ProjectCategoryJpaEntity create(Long projectId, ProjectCategory category) {
        return ProjectCategoryJpaEntity.builder()
                .projectId(projectId)
                .category(category)
                .build();
    }
}
