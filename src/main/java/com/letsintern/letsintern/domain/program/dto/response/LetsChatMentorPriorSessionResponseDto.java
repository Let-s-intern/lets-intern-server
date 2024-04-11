package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatMentorInfoVo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record LetsChatMentorPriorSessionResponseDto(
        LetsChatMentorInfoVo programVo,
        List<String> applyMotiveList,
        List<String> preQuestionList
) {
    public static LetsChatMentorPriorSessionResponseDto of(LetsChatMentorInfoVo programVo, List<String> applyMotiveList, List<String> preQuestionList) {
        return LetsChatMentorPriorSessionResponseDto.builder()
                .programVo(programVo)
                .applyMotiveList(applyMotiveList)
                .preQuestionList(preQuestionList)
                .build();
    }
}
