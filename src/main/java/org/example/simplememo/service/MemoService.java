package org.example.simplememo.service;

import lombok.AllArgsConstructor; // 모든 필드를 인자로 받는 생성자 자동 생성
import org.example.simplememo.dto.MemoRequestDto; // MemoRequestDto import (메모 요청 DTO)
import org.example.simplememo.entity.Memo; // Memo 엔티티 import
import org.example.simplememo.repository.MemoRepository; // MemoRepository import (메모 리포지토리)
import org.springframework.data.domain.Sort; // 정렬 관련 클래스 import
import org.springframework.stereotype.Service; // 이 클래스를 서비스 컴포넌트로 선언

import java.util.List; // List 타입 사용

@Service // 이 클래스를 서비스 컴포넌트로 선언, 스프링의 서비스 계층에서 관리됨
@AllArgsConstructor // 생성자 자동 생성 (MemoRepository 주입)
public class MemoService {

    private final MemoRepository memoRepository; // 메모와 관련된 DB 작업을 처리하는 리포지토리

    /**
     * 메모 생성
     * @param request 생성할 메모의 내용 (MemoRequestDto)
     * @return 생성된 메모 (Memo)
     */
    public Memo createMemo(MemoRequestDto request) {
        Memo memo = request.toEntity(); // DTO를 엔티티로 변환
        return memoRepository.save(memo); // 메모를 DB에 저장
    }

    /**
     * 메모 단건 조회
     * @param memoId 조회할 메모의 ID
     * @return 조회된 메모 (Memo)
     */
    public Memo getMemo(Long memoId) {
        // memoId로 메모를 조회하고, 없으면 예외를 던짐
        return memoRepository.findById(memoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 메모 전체 조회
     * @return 메모 리스트 (List<Memo>)
     */
    public List<Memo> getMemos() {
        return memoRepository.findAll(); // 모든 메모를 조회하여 반환
    }

    /**
     * 메모 수정
     * @param memoId 수정할 메모의 ID
     * @param request 수정할 메모의 내용 (MemoRequestDto)
     * @return 수정된 메모 (Memo)
     */
    public Memo updateMemo(Long memoId, MemoRequestDto request) {
        // 메모 조회 후, 없으면 예외 발생
        Memo memo = getMemo(memoId);
        memo.setTitle(request.getTitle()); // 제목 수정
        memo.setContent(request.getContent()); // 내용 수정
        return memoRepository.save(memo); // 수정된 메모를 DB에 저장
    }

    /**
     * 메모 삭제
     * @param memoId 삭제할 메모의 ID
     */
    public void deleteMemo(Long memoId) {
        // 메모 조회 후, 없으면 예외 발생
        Memo memo = getMemo(memoId);
        memoRepository.delete(memo); // 메모를 DB에서 삭제
    }
}