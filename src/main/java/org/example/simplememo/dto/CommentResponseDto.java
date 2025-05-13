package org.example.simplememo.dto;

import lombok.Getter; // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성
import lombok.Setter; // Lombok 어노테이션: 필드에 대한 setter 메소드 자동 생성
import org.example.simplememo.entity.Comment; // Comment 엔티티 클래스

// 댓글 응답 데이터를 담기 위한 DTO 클래스
@Getter // getter 메소드 자동 생성
@Setter // setter 메소드 자동 생성
public class CommentResponseDto {
    private Long commentId; // 댓글의 ID
    private String content; // 댓글의 내용
    private Long memoId; // 해당 댓글이 속한 메모의 ID

    // Comment 엔티티 객체를 기반으로 CommentResponseDto 객체를 생성하는 생성자
    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId(); // 댓글 ID 설정
        this.memoId = comment.getMemo().getMemoId(); // 댓글이 속한 메모의 ID 설정
        this.content = comment.getContent(); // 댓글 내용 설정
    }
}
