package com.re_teraction.backend.presentation.paper;

import com.re_teraction.backend.application.paper.PaperApplicationService;
import com.re_teraction.backend.application.paper.dto.CreatePaperCommand;
import com.re_teraction.backend.application.paper.dto.PaperResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import com.re_teraction.backend.global.security.resolver.AuthenticatedUserId;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/papers")
@RequiredArgsConstructor
public final class PaperController implements PaperApiDocs {

    private final PaperApplicationService paperApplicationService;

    @GetMapping()
    public ResponseEntity<? extends ApiResponse<?>> getAllPapers() {
        List<PaperResponse> responses = paperApplicationService.getPapers();
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(responses, "논문 조회 성공"));
    }

    @PostMapping()
    public ResponseEntity<? extends ApiResponse<?>> create(
            @AuthenticatedUserId Long userId,
            @RequestBody CreatePaperCommand cmd
    ) {
        PaperResponse response = paperApplicationService.createPaper(userId, cmd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(ApiResponseFactory.success(response, "논문 등록 성공"));
    }
}
