package com.letsintern.letsintern.domain.contents.dto.request;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ContentsCreateDTO {

    @NotNull
    private String title;

    @NotNull
    private ContentsType type;

    @NotNull
    private ContentsTopic topic;

    @NotNull
    private String link;

}
