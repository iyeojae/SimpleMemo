package org.example.simplememo.dto;

import lombok.Getter; // 각 필드의 getter 메서드를 자동 생성
import lombok.Setter; // 각 필드의 setter 메서드를 자동 생성
import org.example.simplememo.entity.Memo; // Memo 엔티티 import

@Getter // title, content에 대한 getter 생성
@Setter // title, content에 대한 setter 생성
public class MemoRequestDto {
    private String title;   // 메모 제목
    private String content; // 메모 내용

    // DTO -> Entity 변환 메서드
    public Memo toEntity() {
        return Memo.builder()          // Memo 엔티티의 빌더 패턴 시작
                .title(title)          // DTO의 title 필드를 Memo 엔티티에 설정
                .content(content)      // DTO의 content 필드를 Memo 엔티티에 설정
                .build();              // Memo 객체 생성 후 반환
    }
}