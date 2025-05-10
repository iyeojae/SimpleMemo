package org.example.simplememo.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션 임포트 (Entity, Column 등)
import lombok.Builder; // Builder 패턴을 사용하기 위한 어노테이션
import lombok.Getter; // Getter 메서드를 자동 생성
import lombok.RequiredArgsConstructor; // 필수 인자를 받는 생성자 자동 생성
import lombok.Setter; // Setter 메서드를 자동 생성

@Getter // 모든 필드에 대해 getter 메서드 자동 생성
@Setter // 모든 필드에 대해 setter 메서드 자동 생성
@Entity // JPA 엔티티 클래스임을 명시
@RequiredArgsConstructor // final 필드를 위한 생성자 자동 생성 (단, 이 클래스에서는 사용되지 않음)
public class Memo {

    @Id // 이 필드는 Primary Key임을 명시
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // SEQUENCE 전략으로 ID 값 생성 (데이터베이스에서 자동 생성)
    @Column(name = "memo_id", nullable = false) // 컬럼명은 "memo_id"로 설정, null 값 불가
    private Long memoId; // 메모 ID (Primary Key)

    private String title; // 메모 제목

    private String content; // 메모 내용

    @Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있게 해줌
    public Memo(String title, String content) {
        this.title = title; // 메모 제목 설정
        this.content = content; // 메모 내용 설정
    }
}