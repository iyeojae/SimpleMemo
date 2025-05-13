package org.example.simplememo.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션을 가져옴
import lombok.*; // Lombok 어노테이션을 가져옴
import org.antlr.v4.runtime.misc.NotNull; // null 값 검사 어노테이션 (사용되지 않음)

// 댓글을 나타내는 엔티티 클래스
@Entity // JPA 엔티티 클래스임을 나타내는 어노테이션
@Getter // Lombok 어노테이션: 필드에 대한 getter 메소드 자동 생성
@Setter // Lombok 어노테이션: 필드에 대한 setter 메소드 자동 생성
@NoArgsConstructor // Lombok 어노테이션: 기본 생성자 자동 생성
public class Comment {

    @Id // 이 필드는 엔티티의 기본 키임을 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 자동으로 설정 (자동 증가)
    private Long commentId; // 댓글 고유 ID

    @Column(nullable = false) // 이 필드는 null을 허용하지 않음 (댓글 내용은 반드시 존재해야 함)
    private String content; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY) // 다수의 댓글이 하나의 메모에 속하는 관계
    @JoinColumn(name = "memo_id", nullable = false) // "memo_id" 컬럼에 외래 키 설정
    private Memo memo; // 댓글이 속한 메모 (다대일 관계)

    // Builder 패턴을 위한 생성자
    @Builder // Lombok의 Builder 패턴을 사용할 수 있도록 해주는 어노테이션
    public Comment(String content, Memo memo) {
        this.content = content; // 댓글 내용 설정
        this.memo = memo; // 댓글이 속한 메모 설정
    }
}
