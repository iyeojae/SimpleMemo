package org.example.simplememo.controller;

import lombok.AllArgsConstructor; // 모든 필드를 인자로 받는 생성자 자동 생성
import lombok.Builder; // 빌더 패턴 사용을 위한 Lombok 어노테이션
import org.example.simplememo.CommonResponse; // 공통 응답 포맷 클래스
import org.example.simplememo.dto.CommentRequestDto; // 댓글 요청 DTO
import org.example.simplememo.dto.CommentResponseDto; // 댓글 응답 DTO
import org.example.simplememo.dto.MemoResponseDto; // 메모 응답 DTO (사용되지 않음)
import org.example.simplememo.entity.Comment; // 댓글 엔티티
import org.example.simplememo.service.CommentService; // 댓글 관련 비즈니스 로직 서비스
import org.springframework.http.ResponseEntity; // HTTP 응답을 표현하는 클래스
import org.springframework.web.bind.annotation.*; // REST API 관련 어노테이션

@RestController // 해당 클래스가 REST 컨트롤러임을 나타냄 (JSON 반환)
@RequestMapping("/api/comments") // "/api/comments" 경로로 시작하는 요청 처리
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자 생성 (의존성 주입용)
public class CommentController {
    private final CommentService commentService; // 댓글 서비스 객체 (의존성 주입)

    @PostMapping("/{memoId}") // POST 요청 처리: 특정 메모에 댓글 작성
    public ResponseEntity<CommonResponse<CommentResponseDto>> postComment(
            @PathVariable Long memoId, // URL에서 메모 ID 추출
            @RequestBody CommentRequestDto request) { // 요청 본문에서 댓글 데이터 추출
        Comment comment = commentService.createComment(memoId, request); // 댓글 생성 서비스 호출
        CommentResponseDto response = new CommentResponseDto(comment); // 댓글 엔티티를 DTO로 변환
        return ResponseEntity.ok() // HTTP 200 응답 반환
                .body(CommonResponse.<CommentResponseDto>builder()
                        .statusCode(200) // 응답 코드
                        .msg("게시글 번호 " + memoId + "댓글 생성이 완료되었습니다.") // 응답 메시지
                        .data(response) // 응답 데이터 (댓글 정보)
                        .build());
    }

    @PutMapping("/{commentId}") // PUT 요청 처리: 댓글 수정
    public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(
            @PathVariable Long commentId, // URL에서 댓글 ID 추출
            @RequestBody CommentRequestDto request) { // 요청 본문에서 수정할 댓글 내용 추출
        Comment comment = commentService.updateComment(commentId, request); // 댓글 수정 서비스 호출
        CommentResponseDto response = new CommentResponseDto(comment); // 수정된 댓글을 DTO로 변환
        return ResponseEntity.ok() // HTTP 200 응답 반환
                .body(CommonResponse.<CommentResponseDto>builder()
                        .statusCode(200) // 응답 코드
                        .msg("댓글 수정이 완료되었습니다.") // 응답 메시지
                        .data(response) // 응답 데이터
                        .build());
    }

    @DeleteMapping("/{commentId}") // DELETE 요청 처리: 댓글 삭제
    public ResponseEntity<CommonResponse<Void>> deleteComment(@PathVariable Long commentId) { // URL에서 댓글 ID 추출
        commentService.deleteComment(commentId); // 댓글 삭제 서비스 호출
        return ResponseEntity.ok() // HTTP 200 응답 반환
                .body(CommonResponse.<Void>builder()
                        .statusCode(200) // 응답 코드
                        .msg("댓글 삭제가 완료되었습니다.") // 응답 메시지
                        .build()); // 반환할 데이터 없음 (Void)
    }
}