package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.user.domain.User;
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
            final User user = application.getUser();
            this.optionalInfo = UserOptionalInfoVo.of(
                    application.getUser().getUniversity(),
                    application.getUser().getMajor()
            );
            this.application.setName(user.getName());
            this.application.setPhoneNum(user.getPhoneNum());
            this.application.setEmail(user.getEmail());
        }
    }
}

