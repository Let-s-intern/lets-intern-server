package com.letsintern.letsintern.domain.notice;

import com.letsintern.letsintern.domain.notice.dto.request.NoticeCreateDTO;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeUpdateDTO;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeIdResponse;
import com.letsintern.letsintern.domain.notice.dto.response.NoticeListResponse;
import com.letsintern.letsintern.domain.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
@Tag(name = "Notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/{programId}")
    @Operation(summary = "어드민 공지사항 생성")
    public NoticeIdResponse createNotice(@PathVariable Long programId, @RequestBody @Valid NoticeCreateDTO noticeCreateDTO) {
        return noticeService.createNotice(programId, noticeCreateDTO);
    }

    @PatchMapping("/{noticeId}")
    @Operation(summary = "어드민 공지사항 수정")
    public NoticeIdResponse updateNotice(@PathVariable Long noticeId, @RequestBody NoticeUpdateDTO noticeUpdateDTO) {
        return noticeService.updateNotice(noticeId, noticeUpdateDTO);
    }

    @GetMapping("/{programId}")
    @Operation(summary = "프로그램 별 공지사항 목록")
    public NoticeListResponse getNoticeList(@PathVariable Long programId, @PageableDefault(size = 10) Pageable pageable) {
        return noticeService.getNoticeList(programId, pageable);
    }
}
