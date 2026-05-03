package com.kai.football_api.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SecurityAspect {
    @Before("@annotation(com.kai.football_api.common.AdminOnly)")
    public void checkAdmin(JoinPoint joinPoint) throws Throwable{
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        if(!email.equals("admin@test.com")){
            throw new CustomException("관리자만 접근 가능합니다.", HttpStatus.FORBIDDEN);
        }
    }
}
