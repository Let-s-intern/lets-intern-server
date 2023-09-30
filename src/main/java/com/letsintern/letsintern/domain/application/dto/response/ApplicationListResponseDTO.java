package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.vo.ApplicationListItemVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationListResponseDTO {

    private List<ApplicationListItemVo> applicationList;

    @Builder
    private ApplicationListResponseDTO(List<ApplicationListItemVo> applicationListItemVoList) {
        this.applicationList = applicationListItemVoList;
    }

    public static ApplicationListResponseDTO from(List<ApplicationListItemVo> applicationListItemVoList) {
        return ApplicationListResponseDTO.builder()
                .applicationListItemVoList(applicationListItemVoList)
                .build();
    }
}
