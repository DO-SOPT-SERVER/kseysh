package com.server.dosopt.seminar.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    // 이렇게 바꾸면 varchar가 아닌 TEXT로 적용된다. content는 긴 글이므로 TEXT로 바꾸어주는 것이 더 적합하다.
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 사람 하나가 여러 post를 가질 수 있으므로, XToOne은 다 지연로딩으로 바꿔준다.
    @JoinColumn(name = "member_id")
    private Member member;
    // Long member_id 처럼 적는 것이 아닌 멤버 객체를 명시해주고, JoinColumn으로 명시를 해준다.
//    private CategoryId categoryId;

    // @ManyToOne 사용을 하지 않고 논리적으로 관계만 맺어둔다.
    @Column(name = "category_id")
    private CategoryId categoryId;

    @Builder
    public Post(String title, String content, Member member){
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
