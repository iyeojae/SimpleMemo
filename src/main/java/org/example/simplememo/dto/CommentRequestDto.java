package org.example.simplememo.dto;

import lombok.Getter; // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성
import lombok.NoArgsConstructor; // Lombok 어노테이션: 기본 생성자 자동 생성
import lombok.Setter; // Lombok 어노테이션: 필드에 대한 setter 메소드 자동 생성
import org.example.simplememo.entity.Comment; // Comment 엔티티 클래스

// 댓글 작성 요청 데이터를 담기 위한 DTO 클래스
@Setter // setter 메소드 자동 생성
@Getter // getter 메소드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
public class CommentRequestDto {
    private String content; // 댓글의 내용

    // CommentRequestDto를 Comment 엔티티로 변환하는 메소드
    public Comment toEntity() {
        return Comment.builder() // Builder 패턴을 사용하여 Comment 엔티티 생성
                .content(content) // 댓글 내용 설정
                .build(); // Comment 객체 생성 후 반환
    }
}
