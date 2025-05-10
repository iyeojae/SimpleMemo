package org.example.simplememo.repository;

import org.example.simplememo.entity.Comment; // Comment 엔티티 import
import org.springframework.data.jpa.repository.JpaRepository; // JPA Repository를 사용하기 위한 인터페이스 import

import java.util.List; // List 타입 사용
import java.util.Optional; // Optional 타입 사용

// JpaRepository를 확장하여 Comment 엔티티에 대한 CRUD 작업을 처리하는 리포지토리 인터페이스
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // JpaRepository를 상속받았기 때문에, 기본적인 CRUD 메서드 (save, findById, delete 등)를 사용할 수 있음.
    // 예를 들어:
    // - save(Comment comment) : 댓글 저장
    // - findById(Long id) : ID로 댓글 조회
    // - deleteById(Long id) : ID로 댓글 삭제
    // - findAll() : 모든 댓글 조회

    // 추가적인 쿼리 메서드를 정의할 수 있음 (필요시 커스터마이즈 가능)
}