package com.re_teraction.backend.presentation;

import com.re_teraction.backend.application.user.UserApplicationService;
import com.re_teraction.backend.application.user.dto.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService userApplicationService;

    @PostMapping()
    public ResponseEntity<Long> create(@RequestBody CreateUserCommand cmd){
        return ResponseEntity.ok(
                userApplicationService.createUser(cmd)
        );
    }
}
