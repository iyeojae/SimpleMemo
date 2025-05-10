package org.example.simplememo.dto;

import lombok.Getter; // Getter 메서드 자동 생성
import lombok.NoArgsConstructor; // 기본 생성자 자동 생성
import lombok.Setter; // Setter 메서드 자동 생성
import org.example.simplememo.entity.Comment; // Comment 엔티티 import
import org.example.simplememo.entity.Memo; // Memo 엔티티 import (하지만 이 클래스에서는 사용되지 않음)

@Setter // content 필드에 대해 setter 자동 생성
@Getter // content 필드에 대해 getter 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
public class CommentRequestDto {
    private String content; // 클라이언트로부터 입력 받을 댓글 내용

    // DTO -> Entity 변환 메서드
    public Comment toEntity() {
        return Comment.builder() // Comment 객체 빌더 패턴 사용
                .content(content) // DTO의 content를 Entity에 세팅
                .build(); // Comment 객체 생성
    }
}