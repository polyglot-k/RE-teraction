package com.re_teraction.backend.domain.project.repo;

import static org.assertj.core.api.Assertions.assertThat;

import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.infra.persistence.dto.ProjectWithThumbnail;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Sql("classpath:sql/project-repo-test-data.sql")
class ProjectRepositoryTest {

    // 테스트 상수
    private static final int EXPECTED_ACADEMIA_COUNT = 2;
    private static final int EXPECTED_SCHOLASTIC_COUNT = 1;
    private static final int EXPECTED_INDUSTRY_COUNT = 0;

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Test
    @DisplayName("모든 프로젝트 조회 시 썸네일이 있는 프로젝트 수 검증")
    void givenProjectsWithThumbnails_whenFindAllWithThumbnail_thenReturnCorrectCount() {
        // When
        List<ProjectWithThumbnail> projects = projectJpaRepository.findAllWithThumbnail();

        // Then
        assertThat(projects)
                .as("썸네일이 있는 모든 프로젝트 수")
                .hasSize(EXPECTED_ACADEMIA_COUNT + EXPECTED_SCHOLASTIC_COUNT
                        + EXPECTED_INDUSTRY_COUNT);
    }

    @Test
    @DisplayName("카테고리별 썸네일 프로젝트 조회 검증")
    void givenProjectsWithCategories_whenFindByCategory_thenReturnExpectedProjects() {
        // Given
        ProjectCategory[] categories = {
                ProjectCategory.ACADEMIA,
                ProjectCategory.SCHOLASTIC,
                ProjectCategory.INDUSTRY
        };

        // When & Then
        for (ProjectCategory category : categories) {
            List<ProjectWithThumbnail> projects = projectJpaRepository.findAllWithThumbnailByCategoriesContaining(
                    category);

            switch (category) {
                case ACADEMIA -> assertThat(projects)
                        .as("Academia 카테고리 프로젝트 수")
                        .hasSize(EXPECTED_ACADEMIA_COUNT);
                case SCHOLASTIC -> assertThat(projects)
                        .as("Scholastic 카테고리 프로젝트 수")
                        .hasSize(EXPECTED_SCHOLASTIC_COUNT);
                case INDUSTRY -> assertThat(projects)
                        .as("Industry 카테고리 프로젝트 수")
                        .hasSize(EXPECTED_INDUSTRY_COUNT);
            }

            if (!projects.isEmpty()) {
                switch (category) {
                    case ACADEMIA -> {
                        java.util.Map<Long, Integer> categoryCounts = projects.stream()
                                .collect(java.util.stream.Collectors.toMap(ProjectWithThumbnail::id,
                                        p -> p.categories().size()));
                        assertThat(categoryCounts).hasSize(2);
                        assertThat(categoryCounts).containsEntry(1L, 2);
                        assertThat(categoryCounts).containsEntry(2L, 1);
                    }
                    case SCHOLASTIC -> assertThat(projects.get(0).categories()).hasSize(2);
                }
            }
        }
    }
}
