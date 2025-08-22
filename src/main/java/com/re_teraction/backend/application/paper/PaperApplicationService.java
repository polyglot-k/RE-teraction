package com.re_teraction.backend.application.paper;

import com.re_teraction.backend.application.paper.dto.CreatePaperCommand;
import com.re_teraction.backend.application.paper.dto.PaperResponse;
import com.re_teraction.backend.domain.paper.PaperJpaEntity;
import com.re_teraction.backend.domain.paper.PaperJpaRepository;
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
    public PaperResponse createPaper(Long userId, CreatePaperCommand command) {
        PaperJpaEntity paper = paperJpaRepository.save(toEntity(userId, command));
        return PaperResponse.from(paper);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<PaperResponse> getPapers() {
        return paperJpaRepository.findAll()
                .stream()
                .map(PaperResponse::from)
                .toList();
    }

    private PaperJpaEntity toEntity(Long userId, CreatePaperCommand command) {
        return PaperJpaEntity.of(command.thumbnailUrl(), command.title(), userId);
    }

    public void deletePaper(Long paperId) {
        paperJpaRepository.deleteById(paperId);
    }
}
