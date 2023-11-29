package com.letsintern.letsintern.domain.faq;

import com.letsintern.letsintern.domain.faq.dto.request.FaqCreateDTO;
import com.letsintern.letsintern.domain.faq.dto.request.FaqUpdateDTO;
import com.letsintern.letsintern.domain.faq.dto.response.FaqIdResponse;
import com.letsintern.letsintern.domain.faq.dto.response.FaqVoListResponse;
import com.letsintern.letsintern.domain.faq.service.FaqService;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @Operation(summary = "FAQ 신규 생성")
    @PostMapping("/{programType}")
    public ResponseEntity<FaqVo> createFaq(@PathVariable ProgramType programType, @RequestBody FaqCreateDTO faqCreateDTO) {
        return ResponseEntity.ok(faqService.createFaq(programType, faqCreateDTO));
    }

    @Operation(summary = "FAQ 수정")
    @PatchMapping("/{faqId}")
    public FaqIdResponse updateFaq(@PathVariable Long faqId, @RequestBody FaqUpdateDTO faqUpdateDTO) {
        return faqService.updateFaq(faqId, faqUpdateDTO);
    }

    @Operation(summary = "프로그램 타입 별 FAQ 목록")
    @GetMapping("/{programType}")
    public FaqVoListResponse getProgramFaqList(@PathVariable ProgramType programType) {
        return faqService.getProgramFaqList(programType);
    }

    @Operation(summary = "FAQ 삭제")
    @DeleteMapping("/{faqId}")
    public void deleteFaq(@PathVariable Long faqId) {
        faqService.deleteFaq(faqId);
    }

}
