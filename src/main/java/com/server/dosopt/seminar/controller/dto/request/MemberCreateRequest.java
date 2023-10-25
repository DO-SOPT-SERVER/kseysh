package com.server.dosopt.seminar.controller.dto.request;

import com.server.dosopt.seminar.domain.SOPT;
import lombok.Data;

@Data
public class MemberCreateRequest {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;
}
