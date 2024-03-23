package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.vo.ProgramMentorInfoVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LetsChatPriorSessionNoticeResponse {

    private ProgramMentorInfoVo programVo;

    private List<String> applyMotiveList;

    private List<String> preQuestionList;

    @Builder
    private LetsChatPriorSessionNoticeResponse(Program program, List<String> applyMotiveList, List<String> preQuestionList) {
        this.programVo = ProgramMentorInfoVo.from(program);
        this.applyMotiveList = applyMotiveList;
        this.preQuestionList = preQuestionList;
    }

    public static LetsChatPriorSessionNoticeResponse of(Program program, List<String> applyMotiveList, List<String> preQuestionList) {
        return LetsChatPriorSessionNoticeResponse.builder()
                .program(program)
                .applyMotiveList(applyMotiveList)
                .preQuestionList(preQuestionList)
                .build();
    }

}
