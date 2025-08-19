package com.re_teraction.backend.domain.user.service;

import com.re_teraction.backend.global.annotation.DomainService;

@DomainService
public class PasswordDomainService {
    public String encode(String plain){
        return plain;
    }
}
