package org.example.simplememo.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.simplememo.entity.Memo;

@Getter
@Setter
public class MemoResponseDto {
    private Long memoId;
    private String title;
    private String content;

    public MemoResponseDto(Memo memo) {
        this.memoId = memo.getMemoId();
        this.title = memo.getTitle();
        this.content = memo.getContent();
    }
}
