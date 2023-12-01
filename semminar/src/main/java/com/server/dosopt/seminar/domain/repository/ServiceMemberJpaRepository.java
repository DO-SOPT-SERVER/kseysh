package com.server.dosopt.seminar.domain.repository;

import com.server.dosopt.seminar.domain.ServiceMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMemberJpaRepository extends JpaRepository<ServiceMember, Long> {
    Optional<ServiceMember> findByNickname(String nickname);
}
