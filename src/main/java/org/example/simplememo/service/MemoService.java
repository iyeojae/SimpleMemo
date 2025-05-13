package org.example.simplememo.service;

import lombok.AllArgsConstructor; // Lombok 어노테이션: 모든 필드에 대한 생성자 자동 생성
import org.example.simplememo.dto.MemoRequestDto; // 메모 생성 및 수정 요청에 필요한 DTO 클래스
import org.example.simplememo.entity.Memo; // Memo 엔티티 클래스
import org.example.simplememo.repository.MemoRepository; // Memo 데이터베이스 연산을 처리하는 리포지토리
import org.springframework.stereotype.Service; // 서비스 컴포넌트임을 나타내는 어노테이션

import java.util.List; // 메모 리스트를 처리하기 위한 List

// 메모에 대한 비즈니스 로직을 처리하는 서비스 클래스
@Service // 이 클래스는 서비스 컴포넌트임을 나타내는 어노테이션
@AllArgsConstructor // 자동으로 모든 필드에 대한 생성자를 생성하는 Lombok 어노테이션
public class MemoService {

    private final MemoRepository memoRepository; // 메모 리포지토리 주입

    /**
     * 메모 생성
     * @param request 생성할 메모의 내용이 담긴 DTO
     * @return 생성된 메모
     */
    public Memo createMemo(MemoRequestDto request) {
        Memo memo = request.toEntity(); // MemoRequestDto를 Memo 엔티티로 변환
        return memoRepository.save(memo); // 메모를 저장하고 반환
    }

    /**
     * 메모 단건 조회
     * @param memoId 조회할 메모의 ID
     * @return 조회된 메모
     */
    public Memo getMemo(Long memoId) {
        // memoId로 메모를 조회하고, 없으면 예외를 던짐
        return memoRepository.findById(memoId)
                .orElseThrow(IllegalArgumentException::new); // 존재하지 않는 메모 ID일 경우 예외를 던짐
    }

    /**
     * 메모 전체 조회
     * @return 메모 리스트
     */
    public List<Memo> getMemos() {
        return memoRepository.findAll(); // 모든 메모를 조회
    }

    /**
     * 메모 수정
     * @param memoId 수정할 메모의 ID
     * @param request 수정할 메모의 내용이 담긴 DTO
     * @return 수정된 메모
     */
    public Memo updateMemo(Long memoId, MemoRequestDto request) {
        Memo memo = getMemo(memoId); // 메모 ID로 메모를 조회
        memo.setTitle(request.getTitle()); // 메모의 제목 수정
        memo.setContent(request.getContent()); // 메모의 내용 수정
        return memoRepository.save(memo); // 수정된 메모를 저장하고 반환
    }

    /**
     * 메모 삭제
     * @param memoId 삭제할 메모의 ID
     */
    public void deleteMemo(Long memoId) {
        Memo memo = getMemo(memoId); // 메모 ID로 메모를 조회
        memoRepository.delete(memo); // 조회된 메모를 삭제
    }
}
