package com.letsintern.letsintern.domain.contents.dto.request;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import lombok.Getter;

import java.util.List;

@Getter
public class ContentsUpdateDTO {

    private String title;

    private ContentsType type;

    private ContentsTopic topic;

    private String link;

    private List<Long> fileIdList;

    private Boolean isVisible;

}
