package org.example.simplememo.controller;

import org.example.simplememo.CommonResponse;
import org.example.simplememo.dto.CommentRequestDto;
import org.example.simplememo.dto.CommentResponseDto;
import org.example.simplememo.entity.Comment;
import org.example.simplememo.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
        Comment comment = commentService.createComment(memoId, request);
        CommentResponseDto response = new CommentResponseDto(comment);
        return ResponseEntity.ok()
            .body(CommonResponse.<CommentResponseDto>builder()
                    .statusCode(200)
                    .msg("게시글 번호" + memoId + "댓글 생성이 완료되었습니다.")
                    .data(response)
                    .build());
}
