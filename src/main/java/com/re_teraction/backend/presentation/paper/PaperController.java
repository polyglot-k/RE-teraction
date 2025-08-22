package com.re_teraction.backend.presentation.paper;

import com.re_teraction.backend.application.paper.PaperApplicationService;
import com.re_teraction.backend.application.paper.dto.CreatePaperCommand;
import com.re_teraction.backend.application.paper.dto.PaperWithThumbnailResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId.AccessType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/papers")
@RequiredArgsConstructor
public final class PaperController implements PaperApiDocs {

    private final PaperApplicationService paperApplicationService;

    @GetMapping()
    public ResponseEntity<? extends ApiResponse<?>> getAllPapers() {
        List<PaperWithThumbnailResponse> responses = paperApplicationService.getPapers();
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(responses, "논문 조회 성공"));
    }

    @PostMapping()
    public ResponseEntity<? extends ApiResponse<?>> create(
            @AuthenticatedUserId Long userId,
            @RequestBody CreatePaperCommand cmd
    ) {
        paperApplicationService.createPaper(userId, cmd);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseFactory.success("논문 등록 성공"));
    }

    @DeleteMapping("/{paperId}")
    public ResponseEntity<? extends ApiResponse<?>> delete(
            @AuthenticatedUserId(accessOnly = AccessType.ADMIN) Long userId,
            @PathVariable Long paperId
    ) {
        paperApplicationService.deletePaper(paperId);
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success("프로젝트 삭제 성공"));
    }
}
