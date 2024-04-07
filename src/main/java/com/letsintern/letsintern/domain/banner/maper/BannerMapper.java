package com.letsintern.letsintern.domain.banner.maper;

import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {

    public BannerIdResponse toBannerIdResponse(Long bannerId) {
        return BannerIdResponse.from(bannerId);
    }

}
