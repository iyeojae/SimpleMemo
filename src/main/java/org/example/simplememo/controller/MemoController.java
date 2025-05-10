package org.example.simplememo.controller;

import lombok.AllArgsConstructor; // 모든 필드를 파라미터로 받는 생성자 자동 생성
import org.example.simplememo.CommonResponse; // 공통 응답 형식 클래스
import org.example.simplememo.dto.MemoRequestDto; // 메모 요청 DTO
import org.example.simplememo.dto.MemoResponseDto; // 메모 응답 DTO
import org.example.simplememo.entity.Memo; // 메모 엔티티
import org.example.simplememo.service.MemoService; // 메모 관련 비즈니스 로직 서비스
import org.springframework.http.HttpStatus; // HTTP 상태 코드
import org.springframework.http.ResponseEntity; // HTTP 응답을 위한 객체
import org.springframework.web.bind.annotation.*; // REST 컨트롤러 관련 어노테이션

import java.util.List; // 리스트 사용을 위한 import
import java.util.stream.Collectors; // 스트림 사용을 위한 import

@RestController // 해당 클래스가 REST API 컨트롤러임을 명시 (JSON 응답)
@AllArgsConstructor // 생성자 주입 방식으로 memoService 주입
@RequestMapping("/api/memo") // 모든 메모 API는 "/api/memo"로 시작
public class MemoController {
    public final MemoService memoService; // 메모 서비스 의존성 주입

    /**
     * 메모 생성 API
     * @param request 생성할 메모의 내용이 담긴 DTO
     * @return 생성된 메모 정보를 담은 응답
     */
    @PostMapping // POST 요청 처리
    public ResponseEntity<CommonResponse<MemoResponseDto>> memoCreate(@RequestBody MemoRequestDto request) {
        Memo memo = memoService.createMemo(request); // 메모 생성 로직 호출
        MemoResponseDto response = new MemoResponseDto(memo); // 엔티티 -> DTO 변환
        return ResponseEntity.ok() // 200 OK 응답 반환
                .body(CommonResponse.<MemoResponseDto>builder()
                        .statusCode(200) // 상태 코드 200
                        .msg("메모 생성이 완료되었습니다.") // 메시지
                        .data(response) // 응답 데이터
                        .build());
    }

    /**
     * 단일 메모 조회 API
     * @param memoId 조회할 메모 ID
     * @return 해당 메모의 상세 정보
     */
    @GetMapping("/{memoId}") // GET 요청, 메모 ID 경로 변수 사용
    public ResponseEntity<CommonResponse<MemoResponseDto>> getMemo(@PathVariable Long memoId) {
        Memo memo = memoService.getMemo(memoId); // 메모 조회 서비스 호출
        MemoResponseDto response = new MemoResponseDto(memo); // 엔티티 -> DTO 변환
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200
                        .msg("단건 조회가 완료 되었습니다.") // 응답 메시지
                        .data(response) // 응답 데이터
                        .build());
    }

    /**
     * 전체 메모 목록 조회 API
     * @return 모든 메모 목록 리스트
     */
    @GetMapping // GET 요청 처리
    public ResponseEntity<CommonResponse<List<MemoResponseDto>>> getMemos() {
        List<Memo> memos = memoService.getMemos(); // 모든 메모 조회
        List<MemoResponseDto> response = memos.stream() // 각 메모를 DTO로 변환
                .map(MemoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<MemoResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200
                        .msg("목록 조회이 완료 되었습니다.") // 응답 메시지
                        .data(response) // 응답 데이터 (메모 리스트)
                        .build());
    }

    /**
     * 메모 수정 API
     * @param memoId 수정할 메모 ID
     * @param request 수정할 메모 데이터
     * @return 수정된 메모 정보
     */
    @PutMapping("/{memoId}") // PUT 요청 처리
    public ResponseEntity<CommonResponse<MemoResponseDto>> updateMemo(
            @PathVariable Long memoId, // URL 경로에서 메모 ID 추출
            @RequestBody MemoRequestDto request) { // 요청 본문에서 수정할 내용 추출
        Memo memo = memoService.updateMemo(memoId, request); // 메모 수정
        MemoResponseDto response = new MemoResponseDto(memo); // 수정된 메모 DTO로 변환
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200
                        .msg("메모 수정이 완료되었습니다.") // 응답 메시지
                        .data(response) // 응답 데이터
                        .build());
    }

    /**
     * 메모 삭제 API
     * @param memoId 삭제할 메모 ID
     */
    @DeleteMapping("/{memoId}") // DELETE 요청 처리
    public ResponseEntity<CommonResponse<Void>> deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId); // 메모 삭제 처리
        return ResponseEntity.ok()
                .body(CommonResponse.<Void>builder()
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200
                        .msg("메모 삭제가 완료되었습니다.") // 응답 메시지
                        .build()); // 반환할 데이터 없음
    }
}