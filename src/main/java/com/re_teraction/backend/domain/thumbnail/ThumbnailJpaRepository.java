package com.re_teraction.backend.domain.thumbnail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThumbnailJpaRepository extends JpaRepository<ThumbnailJpaEntity, Long> {

    @Query("""
                SELECT t
                FROM ThumbnailJpaEntity t
                LEFT JOIN ProjectJpaEntity p ON p.thumbnailId = t.id
                LEFT JOIN PaperJpaEntity pa ON pa.thumbnailId = t.id
                WHERE p.id IS NULL AND pa.id IS NULL
            """)
    Page<ThumbnailJpaEntity> findOrphanThumbnails(Pageable pageable);
}
