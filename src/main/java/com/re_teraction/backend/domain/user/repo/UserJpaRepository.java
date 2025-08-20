package com.re_teraction.backend.domain.user.repo;

import com.re_teraction.backend.domain.user.entity.UserJpaEntity;
import com.re_teraction.backend.domain.user.vo.LoginId;
import com.re_teraction.backend.global.exception.BusinessException;
import com.re_teraction.backend.global.exception.ErrorCode;
import java.util.Optional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByLoginId(LoginId loginId);

    default UserJpaEntity saveAndHandleDuplicate(UserJpaEntity user) {
        try {
            UserJpaEntity saved = save(user);
            flush();
            return saved;
        } catch (DataIntegrityViolationException e) {
            throw mapConstraintViolation(e);
        }
    }

    private BusinessException mapConstraintViolation(DataIntegrityViolationException e) {
        Throwable cause = e.getCause();
        if (cause instanceof ConstraintViolationException cve) {
            String constraint = cve.getConstraintName();
            if ("uk_user_email".equalsIgnoreCase(constraint) ||
                    (constraint != null && constraint.toLowerCase().contains("email"))) {
                return new BusinessException(ErrorCode.DUPLICATE_EMAIL);
            }
            if ("uk_user_login_id".equalsIgnoreCase(constraint) ||
                    (constraint != null && constraint.toLowerCase().contains("login_id"))) {
                return new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
            }
            if ("uk_user_phone_number".equalsIgnoreCase(constraint) ||
                    (constraint != null && constraint.toLowerCase().contains("phone_number"))) {
                return new BusinessException(ErrorCode.DUPLICATE_PHONE_NUMBER);
            }
        }
        return new BusinessException(ErrorCode.USER_CREATION_FAILED);
    }
}
