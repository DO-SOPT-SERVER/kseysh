package com.server.dosopt.seminar.controller.dto.response;

import com.server.dosopt.seminar.domain.Member;

public record MemberGetResponse(
    String name,
    String nickname,
    int age
){
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
            member.getName(),
            member.getNickname(),
            member.getAge()
        );
    }
}
