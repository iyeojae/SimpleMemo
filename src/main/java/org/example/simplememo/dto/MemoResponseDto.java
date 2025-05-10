package org.example.simplememo.dto;

import lombok.Getter; // 각 필드의 getter 메서드 자동 생성
import lombok.Setter; // 각 필드의 setter 메서드 자동 생성
import org.example.simplememo.entity.Memo; // Memo 엔티티 import

@Getter // memoId, title, content에 대한 getter 생성
@Setter // memoId, title, content에 대한 setter 생성
public class MemoResponseDto {
    private Long memoId;   // 메모 ID
    private String title;  // 메모 제목
    private String content; // 메모 내용

    // Memo 엔티티 객체를 받아서 DTO 필드에 값 할당하는 생성자
    public MemoResponseDto(Memo memo) {
        this.memoId = memo.getMemoId();     // 엔티티에서 메모 ID 추출
        this.title = memo.getTitle();       // 엔티티에서 메모 제목 추출
        this.content = memo.getContent();   // 엔티티에서 메모 내용 추출
    }
}