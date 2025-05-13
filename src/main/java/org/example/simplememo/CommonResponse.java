package org.example.simplememo;

import lombok.Builder; // Lombok 어노테이션: 빌더 패턴을 위한 어노테이션
import lombok.Getter; // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성

@Getter // Lombok 어노테이션: 모든 필드에 대한 getter 메소드 자동 생성
@Builder // Lombok 어노테이션: 빌더 패턴을 사용할 수 있게 해주는 어노테이션
public class CommonResponse<T> {
    private Integer statusCode; // HTTP 상태 코드 또는 응답 상태 코드
    private String msg; // 응답 메시지
    private T data; // 실제 응답 데이터 (제네릭 타입을 사용하여 다양한 형태의 데이터를 포함할 수 있음)
}
