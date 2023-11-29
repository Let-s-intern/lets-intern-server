package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.user.vo.UserOptionalInfoVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationAdminVo {

    private Application application;
    private UserOptionalInfoVo optionalInfo;

    @Builder
    public ApplicationAdminVo(Application application) {
        this.application = application;

        if(application.getUser() != null) {
            this.optionalInfo = UserOptionalInfoVo.of(
                    application.getUser().getUniversity(),
                    application.getUser().getMajor()
            );
        }
    }
}

