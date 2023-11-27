package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.controller.dto.request.MemberCreateRequest;
import com.server.dosopt.seminar.controller.dto.request.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.controller.dto.response.MemberGetResponse;
import com.server.dosopt.seminar.service.MemberService;

import java.net.URI;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV1(memberId));
    }

    // 특정 사용자 정보 조회 V2
    @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    // consumes를 이용하여 요청 미디어 타입을 설정
    // produces를 이용하여 응답 미디어 타입을 설정 => application/json으로 작성해도 되지만, 오타가 날 수 있기 때문에 문자열로 하드코딩하지 않은 것
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    // 생성
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        //@RequestBody 어노테이션을 이용하여 MemberCreateRequest 객체로 변환하고 이 객체를 사용해 새로운  Member 객체를 생성한다.
        URI location = URI.create("api/member/" + memberService.create(request));
        // URI 객체를 생성하여 ResponseEntity.create(location)을 통해 반환하면,
        // 새로 생성된 자원의 위치를 location 헤더에서 얻을 수 있게 된다.
        // "api/member" 같은 경로를 추가함으로써 URI를 좀 더 의미 있게 만들 수 있고, 클라이언트가 어떤 자원을 찾을 수 있는 경로를 제공한다.
        // > RESTful API에서는 자원을 의미 있는 경로로 표현하는 것이 일반적이기 때문!
        return ResponseEntity.created(location).build();
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberSoptInfo(@PathVariable Long memberId,
                                                     @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return ResponseEntity.noContent().build();
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
