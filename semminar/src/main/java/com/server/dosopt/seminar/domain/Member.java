package com.server.dosopt.seminar.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 엔티티는 JPA 스펙상 기본생성자가 필요하다.
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private int age;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)// 연관된 엔티티에 대한 모든 변경 작업이 부모 엔티티에 전파되도록 한다.
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, int age) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }
}
