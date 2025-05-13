package org.example.simplememo.dto;

import lombok.Getter; // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성
import lombok.Setter; // Lombok 어노테이션: 필드에 대한 setter 메소드 자동 생성
import org.example.simplememo.entity.Memo; // Memo 엔티티 클래스

// 메모 작성 요청 데이터를 담기 위한 DTO 클래스
@Getter // getter 메소드 자동 생성
@Setter // setter 메소드 자동 생성
public class MemoRequestDto {
    private String title; // 메모의 제목
    private String content; // 메모의 내용

    // MemoRequestDto를 Memo 엔티티로 변환하는 메소드
    public Memo toEntity() {
        return Memo.builder() // Builder 패턴을 사용하여 Memo 엔티티 생성
                .title(title) // 제목 설정
                .content(content) // 내용 설정
                .build(); // Memo 객체 생성 후 반환
    }
}
