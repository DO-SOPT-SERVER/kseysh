package com.server.dosopt.seminar.domain.repository;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);
}

