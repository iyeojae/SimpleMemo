package org.example.simplememo.dto;

import lombok.Getter; // 각 필드에 대한 getter 메서드 자동 생성
import lombok.Setter; // 각 필드에 대한 setter 메서드 자동 생성
import org.example.simplememo.entity.Comment; // Comment 엔티티 import
import org.example.simplememo.entity.Memo; // Memo 엔티티 import (Comment 내부에서 사용됨)

@Getter // 모든 필드에 대한 getter 생성
@Setter // 모든 필드에 대한 setter 생성
public class CommentResponseDto {
    private Long commentId; // 댓글 ID
    private Long memoId;    // 댓글이 속한 메모 ID
    private String content; // 댓글 내용

    // Comment 엔티티를 기반으로 DTO를 생성하는 생성자
    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();           // 댓글 ID 설정
        this.memoId = comment.getMemo().getMemoId();       // 연관된 Memo에서 메모 ID 가져오기
        this.content = comment.getContent();               // 댓글 내용 설정
    }
}