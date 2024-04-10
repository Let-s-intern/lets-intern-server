package com.letsintern.letsintern.domain.program;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.program.domain.MailType;
import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.request.LetsChatMentorPasswordDto;
import com.letsintern.letsintern.domain.program.dto.response.LetsChatMentorAfterSessionResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.LetsChatMentorPriorSessionResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.service.LetsChatServiceImpl;
import com.letsintern.letsintern.domain.program.service.ProgramServiceFactory;
import com.letsintern.letsintern.domain.program.domain.ProgramRequestType;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
@Tag(name = "Program")
public class ProgramController {
    private final ProgramServiceFactory programServiceFactory;
    private final LetsChatServiceImpl letsChatService;

    @Operation(summary = "프로그램 1개 상세 보기")
    @GetMapping("/{programId}")
    public ProgramDetailResponseDto<?> getProgramDetailVo(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                          @PathVariable Long programId,
                                                          @RequestParam ProgramRequestType programRequestType) {
        return programServiceFactory.getProgramService(programRequestType).getProgramDetail(programId, principalDetails);
    }

    @Operation(summary = "어드민 프로그램 1개 상세 보기")
    @GetMapping("/admin/{programId}")
    public BaseProgramResponseDto<?> getProgram(@PathVariable Long programId,
                                                @RequestParam ProgramRequestType programRequestType) {
        return programServiceFactory.getProgramService(programRequestType).getProgramForAdmin(programId);
    }

    @Operation(summary = "어드민 프로그램 신규 개설")
    @PostMapping
    public void createProgram(@RequestParam ProgramRequestType programRequestType,
                              @RequestBody BaseProgramRequestDto requestDto) {
        programServiceFactory.getProgramService(programRequestType).createProgram(requestDto);
    }

    @Operation(summary = "어드민 프로그램 수정")
    @PatchMapping("/{programId}")
    public void updateProgram(@PathVariable Long programId,
                              @RequestParam ProgramRequestType programRequestType,
                              @RequestBody BaseProgramRequestDto requestDto) {
        programServiceFactory.getProgramService(programRequestType).updateProgram(programId, requestDto);
    }

    @Operation(summary = "어드민 프로그램 삭제")
    @DeleteMapping("/{programId}")
    public void deleteProgram(@PathVariable Long programId,
                              @RequestParam ProgramRequestType programRequestType) {
        programServiceFactory.getProgramService(programRequestType).deleteProgram(programId);
    }

    @Operation(summary = "AWS Target Group 상태 확인용")
    @GetMapping("/tg")
    public ResponseEntity<String> targetGroup() {
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "브랜드 스토리 진행 완료 프로그램 개수")
    @GetMapping("/count")
    public ResponseEntity<Long> getProgramCount() {
        return ResponseEntity.ok(programService.getDoneProgramCount());
    }

    @Operation(summary = "프로그램 목록 (전체, 타입 - CHALLENGE, BOOTCAMP, LETS_CHAT)")
    @GetMapping("")
    public ProgramListDTO getProgramThumbnailList(@RequestParam(required = false) String type,
                                                  @PageableDefault(size = 20) Pageable pageable) {
        return programService.getProgramThumbnailList(type, pageable);
    }

    @Operation(summary = "어드민 프로그램 목록 (전체, 타입, 타입&기수)")
    @GetMapping("/admin")
    public AdminProgramListDTO getAdminProgramList(@RequestParam(required = false) String type,
                                                   @RequestParam(required = false) Integer th,
                                                   @PageableDefault(size = 20) Pageable pageable) {
        return programService.getProgramAdminList(type, th, pageable);
    }

    @Operation(summary = "어드민 유저 1명의 프로그램 목록")
    @GetMapping("/admin/user/{userId}")
    public UserProgramVoResponse getAdminUserProgramList(@PathVariable Long userId,
                                                         @PageableDefault(size = 20) Pageable pageable) {
        return programService.getAdminUserProgramList(userId, pageable);
    }

    @Operation(summary = "어드민 렛츠챗 프로그램 1개의 멘토 비밀번호 보기")
    @GetMapping("/admin/{programId}/mentor")
    public LetsChatMentorPasswordDto getProgramMentorPassword(@PathVariable Long programId) {
        return letsChatService.getMentorPassword(programId);
    }

    @Operation(summary = "렛츠챗 프로그램 멘토 세션 안내 페이지 - prior")
    @PostMapping("/{programId}/mentor/prior")
    public LetsChatMentorPriorSessionResponseDto getMentorPriorSessionInfo(@PathVariable Long programId,
                                                                           @RequestBody LetsChatMentorPasswordDto letsChatMentorPasswordDto) {
        return letsChatService.getMentorPriorSessionInfo(programId, letsChatMentorPasswordDto);
    }

    @Operation(summary = "렛츠챗 프로그램 멘토 세션 마무리 페이지 - after")
    @PostMapping("/{programId}/mentor/after")
    public LetsChatMentorAfterSessionResponseDto getMentorAfterSessionInfo(@PathVariable Long programId,
                                                                           @RequestBody LetsChatMentorPasswordDto letsChatMentorPasswordDto) {
        return letsChatService.getMentorAfterSessionInfo(programId, letsChatMentorPasswordDto);
    }

    @Operation(summary = "어드민 프로그램 최종 참여자 수 저장")
    @GetMapping("/admin/{programId}/headcount")
    public void saveFinalHeadCount(@PathVariable Long programId) {
        programService.saveFinalHeadCount(programId);
    }

    @Operation(summary = "어드민 챌린지 프로그램 선발 및 입금 안내, 참여 확정 안내 메일 템플릿")
    @GetMapping("/admin/{programId}/email")
    public ProgramAdminEmailResponse getEmailTemplate(@PathVariable Long programId,
                                                      @RequestParam MailType mailType) {
        return programService.getEmailTemplate(programId, mailType);
    }

    @Operation(summary = "유저 챌린지 대시보드 - 대시보드")
    @GetMapping("/{programId}/dashboard")
    public ProgramDashboardResponse getProgramDashboard(@PathVariable Long programId,
                                                        @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                        @PageableDefault(size = 6) Pageable pageable) {
        return programService.getProgramDashboard(programId, principalDetails, pageable);
    }

    @Operation(summary = "유저 챌린지 대시보드 - 나의 기록장")
    @GetMapping("/{programId}/dashboard/my")
    public ProgramMyDashboardResponse getProgramMyDashboard(@PathVariable Long programId,
                                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return programService.getProgramMyDashboard(programId, principalDetails);
    }

    @Operation(summary = "유저 챌린지 대시보드 - 모두의 기록장")
    @GetMapping("/{programId}/dashboard/entire")
    public ProgramEntireDashboardResponse getProgramEntireDashboard(@PathVariable Long programId,
                                                                    @RequestParam(required = false) ApplicationWishJob applicationWishJob,
                                                                    @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                    @PageableDefault(size = 9) Pageable pageable) {
        return programService.getProgramEntireDashboard(programId, applicationWishJob, principalDetails, pageable);
    }
}
