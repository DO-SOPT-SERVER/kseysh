package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.controller.dto.request.MemberCreateRequest;
import com.server.dosopt.seminar.controller.dto.request.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.controller.dto.response.MemberGetResponse;
import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.SOPT;
import com.server.dosopt.seminar.domain.repository.MemberJpaRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponse getMemberByIdV1(Long id) {
        Member member = memberJpaRepository.findById(id).get();
        return MemberGetResponse.of(member);
    }

    public MemberGetResponse getMemberByIdV2(Long id) {
        return MemberGetResponse.of(memberJpaRepository.findById(id).orElseThrow(
                // findById(id) 메서드는 회원정보를 찾지 못하면 Optional 객체를 반환한다.
            () -> new EntityNotFoundException("존재하지 않는 회원입니다.")));
        // .orElseThrow를 이용하여 값이 없는 경우 예외를 던지게 설정한다.
    }

    public MemberGetResponse getMemberByIdV3(Long id) {
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(id));
    } // 위의 메서드를 함수화 한 것

    public List<MemberGetResponse> getMembers() {
        return memberJpaRepository.findAll()
            .stream()// Member 객체들의 컬렉션을 스트림으로 변환한다.
            .map(MemberGetResponse::of)// 스트림 내의 각 멤버 객체를 DTO를 통해 변환
            .collect(Collectors.toList());// 스트림의 요소를 다시 리스트로 수집한다
    }// 멤버(Member) 객체의 리스트가 MemberGetResponse 객체의 리스트로 변환된 후 반환된다.

    @Transactional // 트랜잭션은 여러 데이터베이스 작업을 논리적으로 그룹화하여 모두 성공하거나 모두 실패하게 하는 데 사용된다.
    public String create(MemberCreateRequest request) {
        Member member =  memberJpaRepository.save(Member.builder()
                // builder 패턴을 사용하여 새로운 Member 객체를 생성한다.
            .name(request.name())
            .nickname(request.nickname())
            .age(request.age())
            .sopt(request.sopt())
            .build());// 최종적으로 Member 객체 생성
        return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(Long memberId, MemberProfileUpdateRequest request) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(request.generation(), request.part()));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        memberJpaRepository.delete(member);
    }
}
