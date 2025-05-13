package org.example.simplememo.dto;

import lombok.Getter; // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성
import lombok.Setter; // Lombok 어노테이션: 필드에 대한 setter 메소드 자동 생성
import org.example.simplememo.entity.Memo; // Memo 엔티티 클래스

// 메모 응답 데이터를 담기 위한 DTO 클래스
@Getter // getter 메소드 자동 생성
@Setter // setter 메소드 자동 생성
public class MemoResponseDto {
    private Long memoId; // 메모의 고유 ID
    private String title; // 메모의 제목
    private String content; // 메모의 내용

    // Memo 엔티티 객체를 기반으로 MemoResponseDto 객체를 생성하는 생성자
    public MemoResponseDto(Memo memo) {
        this.memoId = memo.getMemoId(); // 메모의 ID 설정
        this.title = memo.getTitle(); // 메모의 제목 설정
        this.content = memo.getContent(); // 메모의 내용 설정
    }
}
