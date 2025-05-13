package org.example.simplememo.repository;

import org.example.simplememo.entity.Memo; // Memo 엔티티 클래스
import org.springframework.data.jpa.repository.JpaRepository; // JPA 리포지토리 기본 인터페이스

// 메모 데이터베이스 연산을 처리하기 위한 리포지토리 인터페이스
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // JpaRepository를 확장하여, 기본적인 CRUD 및 페이징, 정렬 기능을 자동으로 제공합니다.
    // Memo 엔티티와 Long 타입의 기본 키를 사용하는 기본 JPA 리포지토리 인터페이스
}
