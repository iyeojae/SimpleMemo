package org.example.simplememo.controller;

import lombok.AllArgsConstructor; // Lombok을 사용하여 모든 생성자를 자동으로 생성하도록 설정
import org.example.simplememo.CommonResponse; // 공통 응답 클래스를 가져옵니다
import org.example.simplememo.dto.MemoRequestDto; // 메모 요청에 필요한 데이터를 담을 DTO 클래스
import org.example.simplememo.dto.MemoResponseDto; // 메모 응답에 필요한 DTO 클래스
import org.example.simplememo.entity.Memo; // 메모 엔티티 클래스
import org.example.simplememo.service.MemoService; // 메모 관련 비즈니스 로직을 처리하는 서비스
import org.springframework.http.HttpStatus; // HTTP 상태 코드
import org.springframework.http.ResponseEntity; // HTTP 응답을 감싸는 객체
import org.springframework.web.bind.annotation.*; // REST API의 각종 HTTP 메소드 관련 어노테이션

import java.util.List; // 메모 리스트를 처리하기 위한 List
import java.util.stream.Collectors; // 스트림을 이용한 리스트 변환

// REST 컨트롤러 클래스 선언
@RestController
@AllArgsConstructor // 자동으로 모든 필드에 대한 생성자를 생성하는 Lombok 어노테이션
@RequestMapping("/api/memo") // "/api/memo"로 시작하는 URL 경로를 처리하는 클래스
public class MemoController {
    public final MemoService memoService; // MemoService를 주입받기 위한 필드

    /**
     * 메모 생성
     * @param request 생성할 메모의 내용
     * @return 생성된 메모
     */
    @PostMapping // POST 요청을 처리하며, 요청 본문에 메모 내용을 담아 전송
    public ResponseEntity<CommonResponse<MemoResponseDto>> memoCreate(@RequestBody MemoRequestDto request) {
        // MemoService에서 메모 생성 비즈니스 로직을 호출
        Memo memo = memoService.createMemo(request);
        // 생성된 메모를 DTO로 변환
        MemoResponseDto response = new MemoResponseDto(memo);

        // HTTP 응답으로 성공 상태 코드와 메시지 및 생성된 메모 데이터를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(200) // 상태 코드 200 (OK)
                        .msg("메모 생성이 완료되었습니다.") // 성공 메시지
                        .data(response) // 생성된 메모 데이터를 포함
                        .build()); // 응답 객체 빌드 후 반환
    }

    /**
     * 메모 단건 조회
     * @param memoId 조회할 메모의 ID
     * @return 조회된 메모
     */
    @GetMapping("/{memoId}") // GET 요청을 처리하며, URL에서 memoId 값을 가져옴
    public ResponseEntity<CommonResponse<MemoResponseDto>> getMemo(@PathVariable Long memoId) {
        // MemoService에서 메모 조회 비즈니스 로직을 호출
        Memo memo = memoService.getMemo(memoId);
        // 조회된 메모를 DTO로 변환
        MemoResponseDto response = new MemoResponseDto(memo);

        // HTTP 응답으로 성공 상태 코드와 메시지 및 조회된 메모 데이터를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200 (OK)
                        .msg("단건 조회가 완료 되었습니다.") // 성공 메시지
                        .data(response) // 조회된 메모 데이터를 포함
                        .build()); // 응답 객체 빌드 후 반환
    }

    /**
     * 메모 전체 조회
     * @return 메모 리스트
     */
    @GetMapping // GET 요청을 처리하며, 모든 메모를 조회
    public ResponseEntity<CommonResponse<List<MemoResponseDto>>> getMemos() {
        // MemoService에서 모든 메모 조회 비즈니스 로직을 호출
        List<Memo> memos = memoService.getMemos();
        // 조회된 모든 메모를 DTO로 변환
        List<MemoResponseDto> response = memos.stream()
                .map(MemoResponseDto::new) // Memo 객체를 MemoResponseDto로 변환
                .collect(Collectors.toList()); // 리스트로 수집

        // HTTP 응답으로 성공 상태 코드와 메시지 및 전체 메모 리스트를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<List<MemoResponseDto>>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200 (OK)
                        .msg("목록 조회이 완료 되었습니다.") // 성공 메시지
                        .data(response) // 메모 리스트 데이터를 포함
                        .build()); // 응답 객체 빌드 후 반환
    }

    /**
     * 메모 수정
     * @param memoId 수정할 메모의 ID
     * @param request 수정할 메모의 내용
     * @return 수정된 메모
     */
    @PutMapping("/{memoId}") // PUT 요청을 처리하며, memoId 값을 URL에서 가져옴
    public ResponseEntity<CommonResponse<MemoResponseDto>> updateMemo(
            @PathVariable Long memoId, // URL에서 memoId 값을 가져옴
            @RequestBody MemoRequestDto request // 수정할 메모 내용을 요청 본문에서 가져옴
    ) {
        // MemoService에서 메모 수정 비즈니스 로직을 호출
        Memo memo = memoService.updateMemo(memoId, request);
        // 수정된 메모를 DTO로 변환
        MemoResponseDto response = new MemoResponseDto(memo);

        // HTTP 응답으로 성공 상태 코드와 메시지 및 수정된 메모 데이터를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200 (OK)
                        .msg("메모 수정이 완료되었습니다.") // 성공 메시지
                        .data(response) // 수정된 메모 데이터를 포함
                        .build()); // 응답 객체 빌드 후 반환
    }

    /**
     * 메모 삭제
     * @param memoId 삭제할 메모의 ID
     */
    @DeleteMapping("/{memoId}") // DELETE 요청을 처리하며, memoId 값을 URL에서 가져옴
    public ResponseEntity<CommonResponse<Void>> deleteMemo(@PathVariable Long memoId) {
        // MemoService에서 메모 삭제 비즈니스 로직을 호출
        memoService.deleteMemo(memoId);

        // HTTP 응답으로 성공 상태 코드와 삭제 완료 메시지를 반환
        return ResponseEntity.ok()
                .body(CommonResponse.<Void>builder() // CommonResponse는 공통 응답 포맷
                        .statusCode(HttpStatus.OK.value()) // 상태 코드 200 (OK)
                        .msg("메모 삭제가 완료되었습니다.") // 성공 메시지
                        .build()); // 응답 객체 빌드 후 반환
    }
}
