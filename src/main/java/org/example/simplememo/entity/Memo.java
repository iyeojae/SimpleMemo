package org.example.simplememo.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션을 가져옴
import lombok.Builder; // Lombok 어노테이션: 빌더 패턴을 위한 어노테이션
import lombok.Getter; // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성
import lombok.RequiredArgsConstructor; // Lombok 어노테이션: 필수 필드를 가진 생성자 자동 생성
import lombok.Setter; // Lombok 어노테이션: 필드에 대한 setter 메소드 자동 생성

// 메모를 나타내는 엔티티 클래스
@Getter // Lombok 어노테이션: 모든 필드에 대한 getter 메소드 자동 생성
@Setter // Lombok 어노테이션: 모든 필드에 대한 setter 메소드 자동 생성
@Entity // JPA 엔티티 클래스임을 나타내는 어노테이션
@RequiredArgsConstructor // Lombok 어노테이션: 필수 필드를 가진 생성자를 자동으로 생성
public class Memo {

    @Id // 이 필드는 엔티티의 기본 키(primary key)로 사용됩니다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 기본 키 생성 전략을 SEQUENCE 방식으로 설정
    @Column(name = "memo_id", nullable = false) // 컬럼 이름을 "memo_id"로 설정, null 허용 안함
    private Long memoId; // 메모 고유 ID

    private String title; // 메모의 제목

    private String content; // 메모의 내용

    // Builder 패턴을 위한 생성자
    @Builder // Lombok 어노테이션: Builder 패턴을 사용할 수 있게 해주는 어노테이션
    public Memo(String title, String content) {
        this.title = title; // 메모의 제목 설정
        this.content = content; // 메모의 내용 설정
    }
}
