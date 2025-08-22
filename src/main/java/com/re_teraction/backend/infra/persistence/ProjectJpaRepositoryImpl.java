package com.re_teraction.backend.infra.persistence;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.re_teraction.backend.domain.project.entity.QProjectCategoryJpaEntity;
import com.re_teraction.backend.domain.project.entity.QProjectJpaEntity;
import com.re_teraction.backend.domain.project.repo.ProjectJpaRepositoryCustom;
import com.re_teraction.backend.domain.project.vo.ProjectCategory;
import com.re_teraction.backend.domain.thumbnail.QThumbnailJpaEntity;
import com.re_teraction.backend.domain.user.entity.QUserJpaEntity;
import com.re_teraction.backend.infra.persistence.dto.ProjectWithThumbnail;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectJpaRepositoryImpl implements ProjectJpaRepositoryCustom {

    private static final QProjectJpaEntity project = QProjectJpaEntity.projectJpaEntity;
    private static final QThumbnailJpaEntity thumbnail = QThumbnailJpaEntity.thumbnailJpaEntity;
    private static final QUserJpaEntity user = QUserJpaEntity.userJpaEntity;
    private static final QProjectCategoryJpaEntity category = QProjectCategoryJpaEntity.projectCategoryJpaEntity;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProjectWithThumbnail> findAllWithThumbnail() {
        return fetchProjects(null);
    }

    @Override
    public List<ProjectWithThumbnail> findAllWithThumbnailByCategoriesContaining(
            ProjectCategory categoryParam) {
        return fetchProjects(categoryParam);
    }

    /**
     * 공통 fetch 메서드
     *
     * @param categoryParam 필터링할 카테고리, null이면 전체 조회
     */
    private List<ProjectWithThumbnail> fetchProjects(ProjectCategory categoryParam) {

        var baseQuery = queryFactory
                .from(project)
                .leftJoin(thumbnail).on(thumbnail.id.eq(project.thumbnailId))
                .leftJoin(user).on(user.id.eq(project.userId))
                .leftJoin(category).on(category.projectId.eq(project.id));

        if (categoryParam != null) {
            baseQuery.where(project.id.in(
                    JPAExpressions
                            .select(category.projectId)
                            .from(category)
                            .where(category.category.eq(categoryParam))
            ));
        }

        return baseQuery.transform(
                GroupBy.groupBy(project.id).list(
                        Projections.bean(ProjectWithThumbnail.class,
                                project.id.as("id"),
                                project.title.as("title"),
                                thumbnail.directory.concat("/").concat(thumbnail.filename)
                                        .coalesce("").as("thumbnailUrl"),
                                user.name.as("author"),
                                GroupBy.set(category.category).as("categories")
                        )
                )
        );
    }
}
