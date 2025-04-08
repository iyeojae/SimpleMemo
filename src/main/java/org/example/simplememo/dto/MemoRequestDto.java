package org.example.simplememo.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.simplememo.entity.Memo;

@Getter
@Setter
public class MemoRequestDto {
    private String title;
    private String content;

    public Memo toEntity() {
        return Memo.builder()
                .title(title)
                .content(content)
                .build();
    }
}
