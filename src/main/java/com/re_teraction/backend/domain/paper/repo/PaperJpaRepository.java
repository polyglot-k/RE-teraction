package com.re_teraction.backend.domain.paper.repo;

import com.re_teraction.backend.domain.paper.entity.PaperJpaEntity;
import com.re_teraction.backend.infra.persistence.dto.PaperWithThumbnail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaperJpaRepository extends JpaRepository<PaperJpaEntity, Long> {

    @Query("""
                SELECT new com.re_teraction.backend.infra.persistence.dto.PaperWithThumbnail(
                    p.id,
                    p.title,
                    u.name,
                    CONCAT(t.directory, '/', t.filename)
                )
                FROM PaperJpaEntity p
                LEFT JOIN ThumbnailJpaEntity t ON p.thumbnailId = t.id
                LEFT JOIN UserJpaEntity u ON p.userId = u.id
            """)
    List<PaperWithThumbnail> findAllWithThumbnail();
}
