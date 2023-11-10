package com.letsintern.letsintern.domain.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCreateDTO {

    private Integer grade;

    private String reviewContents;

    private String suggestContents;

}
