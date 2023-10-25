package com.server.dosopt.seminar.domain;


import static jakarta.persistence.EnumType.STRING;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 파라미터 없는 기본 생성자를 자동으로 생성한다.
// access 속성은 생성자의 접근 제어자를 PROTECTED로 지정한다.
@AllArgsConstructor
// 모든 필드를 인자로 받는 생성자를 자동으로 생성한다.
public class SOPT {
    private int generation;

    @Enumerated(value = STRING) // 이 어노테이션을 이용해야 Part의 변수명으로 저장을 할 수 있다.
    private Part part;
}
