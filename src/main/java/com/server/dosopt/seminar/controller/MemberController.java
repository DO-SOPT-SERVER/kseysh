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
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    // 특정 사용자 정보 단건 조회 V2
    @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    // consumes를 이용하여 요청 미디어 타입을 설정
    // produces를 이용하여 응답 미디어 타입을 설정
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    // 생성
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        //@RequestBody 어노테이션을 이용하여 MemberCreateRequest 객체로 변환하고 이 객체를 사용해 새로운  Member 객체를 생성한다.
        URI location =  URI.create(memberService.create(request));
        // URI 객체를 생성하여 ResponseEntity.create(location)을 통해 반환하면,
        // 새로 생성된 자원의 위치를 location 헤더에서 얻을 수 있게 된다.
        return ResponseEntity.created(location).build();
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
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
