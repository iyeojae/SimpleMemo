package org.example.simplememo.service;

import lombok.AllArgsConstructor; // Lombok 어노테이션: 모든 필드에 대한 생성자 자동 생성
import org.example.simplememo.dto.CommentRequestDto; // 댓글 요청에 필요한 데이터를 담을 DTO 클래스
import org.example.simplememo.entity.Comment; // Comment 엔티티 클래스
import org.example.simplememo.entity.Memo; // Memo 엔티티 클래스
import org.example.simplememo.repository.CommentRepository; // Comment 리포지토리 인터페이스
import org.example.simplememo.repository.MemoRepository; // Memo 리포지토리 인터페이스
import org.springframework.stereotype.Service; // Service 컴포넌트를 정의하는 어노테이션

// 댓글 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service // 이 클래스는 서비스 컴포넌트임을 나타내는 어노테이션
@AllArgsConstructor // 자동으로 모든 필드에 대한 생성자를 생성하는 Lombok 어노테이션
public class CommentService {

    private final CommentRepository commentRepository; // 댓글에 대한 데이터베이스 작업을 처리하는 리포지토리
    private final MemoRepository memoRepository; // 메모에 대한 데이터베이스 작업을 처리하는 리포지토리

    /**
     * 댓글 생성
     * @param memoId 댓글이 달릴 메모의 ID
     * @param request 댓글 내용이 담긴 DTO
     * @return 생성된 댓글
     */
    public Comment createComment(Long memoId, CommentRequestDto request) {
        // 메모 ID로 메모를 조회
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메모 아이디: " + memoId));
        // 조회된 메모와 댓글 내용으로 새로운 댓글 생성
        Comment comment = new Comment(request.getContent(), memo);
        // 댓글을 저장하고 반환
        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     * @param commentId 수정할 댓글의 ID
     * @param request 수정할 댓글 내용이 담긴 DTO
     * @return 수정된 댓글
     */
    public Comment updateComment(Long commentId, CommentRequestDto request) {
        // 댓글 ID로 댓글을 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 아이디: " + commentId));
        // 댓글 내용 수정
        comment.setContent(request.getContent());
        // 수정된 댓글을 저장하고 반환
        return commentRepository.save(comment);
    }

    /**
     * 댓글 삭제
     * @param commentId 삭제할 댓글의 ID
     */
    public void deleteComment(Long commentId) {
        // 댓글 ID로 댓글을 삭제
        commentRepository.deleteById(commentId);
    }
}
