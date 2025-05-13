package org.example.simplememo.controller;

import lombok.AllArgsConstructor; // Lombok을 사용하여 모든 생성자를 자동으로 생성하도록 설정
import lombok.Builder; // Lombok을 사용하여 Builder 패턴을 지원하도록 설정
import org.example.simplememo.CommonResponse; // 공통 응답 클래스를 가져옵니다
import org.example.simplememo.dto.CommentRequestDto; // 댓글 작성 요청에 필요한 데이터를 담을 DTO 클래스
import org.example.simplememo.dto.CommentResponseDto; // 댓글 생성/수정 후 응답으로 보낼 DTO 클래스
import org.example.simplememo.entity.Comment; // 댓글 엔티티 클래스
import org.example.simplememo.service.CommentService; // 댓글 관련 비즈니스 로직을 처리하는 서비스
import org.springframework.http.ResponseEntity; // HTTP 응답을 감싸는 객체
import org.springframework.web.bind.annotation.*; // REST API의 각종 HTTP 메소드 관련 어노테이션

// REST 컨트롤러 클래스 선언
@RestController
@RequestMapping("/api/comments") // "/api/comments"로 시작하는 URL 경로를 처리하는 클래스
@AllArgsConstructor // 자동으로 모든 필드에 대한 생성자를 생성하는 Lombok 어노테이션
public class CommentController {
    private final CommentService commentService; // CommentService를 주입받기 위한 필드

    // 댓글 생성 처리
    @PostMapping("/{memoId}") // POST 요청을 처리하며, memoId를 URL 경로에서 변수로 사용
    public ResponseEntity<CommonResponse<CommentResponseDto>> postComment(
            @PathVariable Long memoId, // URL에서 memoId 값을 가져옴
            @RequestBody CommentRequestDto request // 요청 본문에서 CommentRequestDto 객체를 가져옴
    ) {
        // CommentService에서 댓글 생성 비즈니스 로직을 호출
        Comment comment = commentService.createComment(memoId, request);
        // 생성된 댓글을 DTO로 변환
        CommentResponseDto response = new CommentResponseDto(comment);

        // HTTP 응답으로 성공 상태 코드와 메시지 및 데이터를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<CommentResponseDto>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(200) // 상태 코드 200 (OK)
                        .msg("게시글 번호 " + memoId + "댓글 생성이 완료되었습니다.") // 성공 메시지
                        .data(response) // 댓글 생성 후 데이터를 포함
                        .build()); // 응답 객체 빌드 후 반환
    }

    // 댓글 수정 처리
    @PutMapping("/{commentId}") // PUT 요청을 처리하며, commentId를 URL 경로에서 변수로 사용
    public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(
            @PathVariable Long commentId, // URL에서 commentId 값을 가져옴
            @RequestBody CommentRequestDto request // 요청 본문에서 CommentRequestDto 객체를 가져옴
    ) {
        // CommentService에서 댓글 수정 비즈니스 로직을 호출
        Comment comment = commentService.updateComment(commentId, request);
        // 수정된 댓글을 DTO로 변환
        CommentResponseDto response = new CommentResponseDto(comment);

        // HTTP 응답으로 성공 상태 코드와 메시지 및 수정된 데이터를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<CommentResponseDto>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(200) // 상태 코드 200 (OK)
                        .msg("댓글 수정이 완료되었습니다.") // 성공 메시지
                        .data(response) // 수정된 댓글 데이터를 포함
                        .build()); // 응답 객체 빌드 후 반환
    }

    // 댓글 삭제 처리
    @DeleteMapping("/{commentId}") // DELETE 요청을 처리하며, commentId를 URL 경로에서 변수로 사용
    public ResponseEntity<CommonResponse<Void>> deleteComment(
            @PathVariable Long commentId // URL에서 commentId 값을 가져옴
    ) {
        // CommentService에서 댓글 삭제 비즈니스 로직을 호출
        commentService.deleteComment(commentId);

        // HTTP 응답으로 성공 상태 코드와 삭제 완료 메시지를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<Void>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(200) // 상태 코드 200 (OK)
                        .msg("댓글 삭제가 완료되었습니다.") // 성공 메시지
                        .build()); // 응답 객체 빌드 후 반환
    }
}
