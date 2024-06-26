package com.letsintern.letsintern.domain.memo;

import com.letsintern.letsintern.domain.memo.dto.request.MemoDTO;
import com.letsintern.letsintern.domain.memo.dto.response.MemoIdResponse;
import com.letsintern.letsintern.domain.memo.dto.response.MemoListResponse;
import com.letsintern.letsintern.domain.memo.service.MemoService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
@Tag(name = "Memo")
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "메모 생성")
    @PostMapping("/{targetUserId}")
    public MemoIdResponse createMemo(
            @PathVariable Long targetUserId,
            @RequestBody MemoDTO memoDTO,
            @AuthenticationPrincipal PrincipalDetails principal) {
        return memoService.createMemo(targetUserId, memoDTO, principal);
    }

    @Operation(summary = "메모 수정")
    @PatchMapping("/{memoId}")
    public MemoIdResponse updateMemo(@PathVariable Long memoId, @RequestBody MemoDTO memoDTO) {
        return memoService.updateMemo(memoId, memoDTO);
    }

    @Operation(summary = "사용자 별 메모 목록 불러오기")
    @GetMapping("/{targetUserId}")
    public MemoListResponse getMemoListOfUser(
            @PathVariable Long targetUserId,
            @PageableDefault(size = 20) Pageable pageable) {
        return memoService.getMemoListOfUser(targetUserId, pageable);
    }

    @Operation(summary = "메모 삭제")
    @DeleteMapping("/{memoId}")
    public void deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
    }
}
