package org.example.simplememo.service;

import lombok.AllArgsConstructor; // 모든 필드를 인자로 받는 생성자 자동 생성
import org.example.simplememo.dto.CommentRequestDto; // CommentRequestDto import (댓글 요청 DTO)
import org.example.simplememo.entity.Comment; // Comment 엔티티 import
import org.example.simplememo.entity.Memo; // Memo 엔티티 import
import org.example.simplememo.repository.CommentRepository; // CommentRepository import (댓글 리포지토리)
import org.example.simplememo.repository.MemoRepository; // MemoRepository import (메모 리포지토리)
import org.springframework.stereotype.Service; // 이 클래스를 서비스 컴포넌트로 선언

@Service // 이 클래스는 서비스 컴포넌트로, 스프링 컨테이너에서 관리됨
@AllArgsConstructor // 생성자 자동 생성 (CommentRepository와 MemoRepository를 주입받음)
public class CommentService {

    private final CommentRepository commentRepository; // 댓글 관련 DB 작업을 처리할 리포지토리
    private final MemoRepository memoRepository; // 메모 관련 DB 작업을 처리할 리포지토리

    // 댓글 생성 메서드
    public Comment createComment(Long memoId, CommentRequestDto request) {
        // memoId로 메모를 조회, 메모가 존재하지 않으면 예외 발생
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메모 아이디: " + memoId));

        // 댓글 객체 생성 (DTO에서 전달된 내용과 메모 객체를 이용)
        Comment comment = new Comment(request.getContent(), memo);

        // 댓글을 DB에 저장
        return commentRepository.save(comment);
    }

    // 댓글 수정 메서드
    public Comment updateComment(Long commentId, CommentRequestDto request) {
        // commentId로 댓글을 조회, 댓글이 존재하지 않으면 예외 발생
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 아이디: " + commentId));

        // 댓글 내용 수정
        comment.setContent(request.getContent());

        // 수정된 댓글을 DB에 저장
        return commentRepository.save(comment);
    }

    // 댓글 삭제 메서드
    public void deleteComment(Long commentId) {
        // commentId로 댓글을 삭제 (DB에서 삭제)
        commentRepository.deleteById(commentId);
    }
}