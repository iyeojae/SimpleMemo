package org.example.simplememo.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션 임포트 (Entity, Column 등)
import lombok.*; // Lombok 관련 어노테이션 (Getter, Setter, NoArgsConstructor, Builder 등)
import org.antlr.v4.runtime.misc.NotNull; // NotNull 어노테이션 (사용되지 않음)

@Entity // JPA 엔티티 클래스임을 명시
@Getter // 모든 필드에 대해 getter 메서드 자동 생성
@Setter // 모든 필드에 대해 setter 메서드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
public class Comment {

    @Id // 이 필드는 Primary Key임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스에서 자동으로 값 생성 (자동 증가)
    private Long commentId; // 댓글 ID (Primary Key)

    @Column(nullable = false) // 이 컬럼은 null 값이 허용되지 않음
    private String content; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY) // 댓글은 여러 개가 하나의 메모에 속하는 관계 (Many-to-One)
    @JoinColumn(name = "memo_id", nullable = false) // 외래 키로 memo_id를 사용 (메모와의 관계를 설정)
    private Memo memo; // 댓글이 속한 메모 (Memo 엔티티와 연관)

    @Builder // 빌더 패턴을 사용하여 객체 생성 가능
    public Comment(String content, Memo memo) {
        this.content = content; // 댓글 내용 설정
        this.memo = memo; // 댓글이 속할 메모 설정
    }
}