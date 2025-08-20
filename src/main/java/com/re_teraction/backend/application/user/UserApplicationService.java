package com.re_teraction.backend.application.user;

import com.re_teraction.backend.application.user.dto.CreateUserCommand;
import com.re_teraction.backend.application.user.dto.UserResponse;
import com.re_teraction.backend.domain.user.entity.UserJpaEntity;
import com.re_teraction.backend.domain.user.repo.UserJpaRepository;
import com.re_teraction.backend.domain.user.service.UserAssembler;
import com.re_teraction.backend.domain.user.vo.Email;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.domain.user.vo.Password;
import com.re_teraction.backend.domain.user.vo.PhoneNumber;
import com.re_teraction.backend.global.annotation.ApplicationService;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import com.re_teraction.backend.global.security.crypto.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Slf4j
public class UserApplicationService {

    private final UserJpaRepository userJpaRepository;
    private final UserAssembler userAssembler;

    private final PasswordEncryptor passwordEncryptor;

    @Transactional
    public UserResponse createUser(CreateUserCommand cmd) {
        UserJpaEntity user = userJpaRepository.saveAndHandleDuplicate(toEntity(cmd));
        return UserResponse.from(user);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserResponse getUserById(Long userId) {
        return userJpaRepository.findById(userId)
                .map(UserResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserJpaEntity getUserByLoginId(String loginId) {
        return userJpaRepository.findByLoginId(LoginId.of(loginId))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    private UserJpaEntity toEntity(CreateUserCommand cmd) {
        LoginId loginId = userAssembler.toLoginId(cmd.loginId());
        Email email = userAssembler.toEmail(cmd.email());
        PhoneNumber phoneNumber = userAssembler.toPhoneNumber(cmd.phoneNumber());
        Password password = passwordEncryptor.encrypt(cmd.password());

        return UserJpaEntity.of(loginId, password, cmd.name(), email, phoneNumber);
    }
}