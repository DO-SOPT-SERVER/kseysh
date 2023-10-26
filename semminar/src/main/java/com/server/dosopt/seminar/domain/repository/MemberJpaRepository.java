package com.server.dosopt.seminar.domain.repository;

import com.server.dosopt.seminar.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    // JpaRepository는 JpaRepository<Entity, Entity의 식별자의 타입> 형태로 작성 후에 상속하여 사용할 수 있다.
    // Entity의 식별자가 Long인 이유는 Member Entity의 id를 Long으로 지정했기 때문
    // JpaRepository를 이용해 따로 method를 작성하지 않아도 기본적인 CRUD를 사용할 수 있다.
    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            // findById : Spring Data JPA가 자동으로 생성한 메서드로, id를 기반으로 Member 엔티티를 찾아 반환한다.
            // Optional<Member> 타입을 반환한다.
            // orElseThrow : Optional 객체가 비어있을 때 지정된 예외를 반환하는 함수
            () -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }

}
