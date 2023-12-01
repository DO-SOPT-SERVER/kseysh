package com.server.dosopt.seminar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ServiceMember {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    private String password;

    @Builder
    public ServiceMember(String nickName, String password) {
        this.nickname = nickName;
        this.password = password;
    }
}
