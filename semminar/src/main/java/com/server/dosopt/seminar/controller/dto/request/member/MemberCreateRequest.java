package com.server.dosopt.seminar.controller.dto.request.member;


public record MemberCreateRequest(
        String name,
        String nickname,
        int age
){}
