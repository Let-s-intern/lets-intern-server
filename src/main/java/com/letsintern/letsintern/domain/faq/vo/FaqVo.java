package com.letsintern.letsintern.domain.faq.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqVo {

    private Long id;
    private String question;
    private String answer;

    @Builder
    public FaqVo(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public static FaqVo of(Long id, String question, String answer) {
        return FaqVo.builder()
                .id(id)
                .question(question)
                .answer(answer)
                .build();
    }
}
