package org.example.simplememo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.simplememo.dto.CommentRequestDto;
import org.example.simplememo.entity.Comment;
import org.example.simplememo.entity.Memo;
import org.example.simplememo.repository.CommentRepository;
import org.example.simplememo.repository.MemoRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;

    public Comment createComment(Long memoId, CommentRequestDto request){
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 메모 아이디" + memoId));
        Comment comment = new Comment(request.getContent(), memo);
        return commentRepository.save(comment);
    }

}
