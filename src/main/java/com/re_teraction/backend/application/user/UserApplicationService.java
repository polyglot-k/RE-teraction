package com.re_teraction.backend.application.user;

import com.re_teraction.backend.application.user.dto.CreateUserCommand;
import com.re_teraction.backend.application.user.dto.UserResponse;
import com.re_teraction.backend.domain.user.vo.Email;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.domain.user.vo.Password;
import com.re_teraction.backend.domain.user.vo.PhoneNumber;
import com.re_teraction.backend.domain.user.entity.UserJpaEntity;
import com.re_teraction.backend.domain.user.repo.UserJpaRepository;
import com.re_teraction.backend.domain.user.service.UserMapper;
import com.re_teraction.backend.global.annotation.ApplicationService;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Slf4j
public class UserApplicationService {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(CreateUserCommand cmd) {
        UserJpaEntity user =  userJpaRepository.saveAndHandleDuplicate(toEntity(cmd));
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserResponse getUserById(Long userId) {
        return userJpaRepository.findById(userId)
                .map(UserResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    private UserJpaEntity toEntity(CreateUserCommand cmd) {
        LoginId loginId = userMapper.toLoginId(cmd.loginId());
        Email email = userMapper.toEmail(cmd.email());
        PhoneNumber phoneNumber = userMapper.toPhoneNumber(cmd.phoneNumber());
        Password password = userMapper.toPassword(cmd.password());

        return UserJpaEntity.of(loginId, password, cmd.name(), email, phoneNumber);
    }
}