package com.re_teraction.backend.infra.file.scheduler;

import com.re_teraction.backend.domain.thumbnail.ThumbnailJpaEntity;
import com.re_teraction.backend.domain.thumbnail.ThumbnailJpaRepository;
import com.re_teraction.backend.global.scheduler.DelayedScheduler;
import com.re_teraction.backend.infra.s3.S3Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3OrphanProcessor implements FileOrphanProcessor {

    private static final int BATCH_SIZE = 100;
    private final DelayedScheduler delayedScheduler;
    private final ThumbnailJpaRepository repository;
    private final S3Service s3Service;

    @Override
    public void process() {
        delayedScheduler.execute(this::processAllBatches);
    }

    private void processAllBatches() {
        boolean hasMore = true;
        int page = 0;
        while (hasMore) {
            Page<ThumbnailJpaEntity> orphanThumbnails = repository.findOrphanThumbnails(
                    PageRequest.of(page, BATCH_SIZE));

            if (orphanThumbnails.isEmpty()) {
                log.info("No orphan thumbnails found.");
                break;
            }

            log.info("Found {} orphan thumbnails.", orphanThumbnails.getContent().size());

            List<ThumbnailJpaEntity> deletedSuccessfully = deleteS3Files(
                    orphanThumbnails.getContent());
            deleteFromDB(deletedSuccessfully);
            hasMore = orphanThumbnails.hasNext();
            page++;
        }
    }

    private List<ThumbnailJpaEntity> deleteS3Files(List<ThumbnailJpaEntity> orphanThumbnails) {
        return orphanThumbnails.stream()
                .filter(thumbnail -> {
                    var key = thumbnail.getDirectory() + "/" + thumbnail.getFilename();
                    return s3Service.delete(key);
                })
                .toList();
    }

    private void deleteFromDB(List<ThumbnailJpaEntity> thumbnails) {
        if (thumbnails.isEmpty()) {
            return;
        }

        repository.deleteAll(thumbnails);
        log.info("Deleted {} orphan thumbnails from DB.", thumbnails.size());
    }
}
