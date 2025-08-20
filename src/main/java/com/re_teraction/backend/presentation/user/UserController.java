package com.re_teraction.backend.presentation.user;

import com.re_teraction.backend.application.user.UserApplicationService;
import com.re_teraction.backend.application.user.dto.CreateUserCommand;
import com.re_teraction.backend.application.user.dto.UserResponse;
import com.re_teraction.backend.global.response.ApiResponse;
import com.re_teraction.backend.global.response.ApiResponseFactory;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public final class UserController implements UserApiDocs {

    private final UserApplicationService userApplicationService;

    @GetMapping("/{userId}")
    public ResponseEntity<? extends ApiResponse<?>> getById(@PathVariable Long userId) {
        UserResponse response = userApplicationService.getUserById(userId);
        return ResponseEntity
                .ok()
                .body(ApiResponseFactory.success(response, "사용자 조회 성공"));
    }

    @PostMapping()
    public ResponseEntity<? extends ApiResponse<?>> create(@RequestBody CreateUserCommand cmd) {
        UserResponse response = userApplicationService.createUser(cmd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(ApiResponseFactory.success(response, "회원가입 성공"));
    }
}
