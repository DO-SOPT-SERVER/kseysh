package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.HealthCheckResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 해당 클래스가 RESTful 웹 서비스의 엔드포인트를 처리하는 컨트롤러 클래스임을 나타내는 클래스
@RequestMapping("/health")
// 클래스 수준에서 URL 경로를 지정하는 클래스
public class HealthCheckController {
    @GetMapping("/v1")
    // HTTP GET 요청에 대한 처리를 나타내는 어노테이션
    public Map<String, String> healthCheck(){
        Map<String, String> response = new HashMap<>();
        response.put("status","OK");
        response.put("stat","NO"); // HashMap은 중복 key 값이 발생했을 시 key의 value값을 덮어씌운다!
        return response;

    }

    @GetMapping("/v2")
    public ResponseEntity<String> healthCheckV2(){
        return ResponseEntity.ok("OK");
    }
    // ResponseEntity : Spring 프레임워크에서 HTTP 응답을 생성하고 제어하기 위한 클래스
    // HTTP 응답의 상태 코드, 헤더 및 응답 본문을 설정할 수 있다.
    // ResponseEntity.ok()는 HTTP 200 OK 상태 코드를 반환한다.
    // ResponseEntity.notFound()는 HTTP 404 NOT Found 상태를 반환한다.
    // header() 메소드를 통해 특정 헤더 값도 설정이 가능하다.
    @GetMapping("/v3")
    public String healthCheckV3(){
        return "OK";
    }

    @GetMapping("/v4")
    public ResponseEntity<Map<String,String>>  healthCheckv4(){
        Map<String, String> response = new HashMap<>();
        response.put("status","ok");
        return ResponseEntity.ok(response);
    }
    // ResponseEntity는 public class ResponseEntity<T> extends HttpEntity<T>로 되어 있으며,
    // T라는 형식 매개변수를 가져 다양한 데이터 형식(여기서 사용된 String, Map 등)을 반환할 수 있다.
    // 또한 HttpEntity 클래스를 확장하여 HTTP 요청 및 응답에 대한 정보를 다룸으로써  HTTP 응답과 관련된 데이터를 포함한다.


    @GetMapping("/v5")
    public ResponseEntity<HealthCheckResponse> healthCheckV5(){
        return ResponseEntity.ok(new HealthCheckResponse());
    }
    // 406 에러 발생 => 직렬화를 위해서는 Getter 메소드가 필요한데, Getter 어노테이션을 추가해줌으로써 해결
    // 직렬화는 Java Object를 Json으로 변환하는 것이다.
    // ResponseEntity는 직렬화를 통해 json형식으로 값을 반환해주는데,
    // 객체를 Json으로 변환하기 위해서는 해당 객체의 field에 접근해야 한다.
    // 이를 위해서는 private로 선언된 변수에 접근해야 하는데, 이를 위해서 Getter 메서드가 필요하다.

}
