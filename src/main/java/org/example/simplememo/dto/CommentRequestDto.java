package org.example.simplememo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.simplememo.entity.Comment;
import org.example.simplememo.entity.Memo;

@Setter
@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
