package com.server.dosopt.seminar.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate// now의 시간을 넣어준다.
    private LocalDateTime createdAt;

    @LastModifiedDate// 수정했을 때의 시간을 넣어준다.
    private LocalDateTime updatedAt;// 날짜를 표현할 때는 ~~At ~~Date 처럼 만든다 (DB 컨벤션)

}
