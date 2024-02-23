package com.letsintern.letsintern.domain.application.dto.request;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import lombok.Getter;

@Getter
public class ApplicationChallengeUpdateDTO {

    private String introduction;

    private ApplicationWishJob wishJob;
}
