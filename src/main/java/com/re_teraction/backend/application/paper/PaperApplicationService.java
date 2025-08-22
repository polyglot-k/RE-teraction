package com.re_teraction.backend.application.paper;

import com.re_teraction.backend.application.paper.dto.CreatePaperCommand;
import com.re_teraction.backend.application.paper.dto.PaperWithThumbnailResponse;
import com.re_teraction.backend.domain.paper.entity.PaperJpaEntity;
import com.re_teraction.backend.domain.paper.repo.PaperJpaRepository;
import com.re_teraction.backend.global.annotation.ApplicationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
public class PaperApplicationService {

    private final PaperJpaRepository paperJpaRepository;

    @Transactional
    public void createPaper(Long userId, CreatePaperCommand command) {
        paperJpaRepository.save(toEntity(userId, command));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<PaperWithThumbnailResponse> getPapers() {
        //thumbnail id 추가 join
        return paperJpaRepository.findAllWithThumbnail()
                .stream()
                .map(PaperWithThumbnailResponse::from)
                .toList();
    }

    private PaperJpaEntity toEntity(Long userId, CreatePaperCommand command) {
        return PaperJpaEntity.of(command.thumbnailId(), command.title(), userId);
    }

    public void deletePaper(Long paperId) {
        paperJpaRepository.deleteById(paperId);
    }
}
