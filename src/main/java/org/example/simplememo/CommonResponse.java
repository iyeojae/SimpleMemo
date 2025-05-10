package org.example.simplememo;

import lombok.Builder; // Lombok의 Builder 어노테이션을 사용하여 빌더 패턴 적용
import lombok.Getter; // Lombok의 Getter 어노테이션을 사용하여 모든 필드에 대한 getter 메소드 자동 생성

@Getter // 클래스에 대한 getter 메소드 자동 생성
@Builder // 빌더 패턴을 사용할 수 있도록 생성자를 자동 생성
public class CommonResponse<T> {
    private Integer statusCode; // HTTP 상태 코드 (예: 200, 400 등)
    private String msg; // 상태 메시지 (예: "성공", "실패", "에러 발생" 등)
    private T data; // API 응답 데이터 (제네릭 타입으로, 응답하는 데이터의 종류가 유동적임)

    // 기본 생성자는 Lombok에 의해 자동으로 생성됩니다.
}