package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.BannerType;

public interface BannerServiceFactory {
    BannerService getBannerService(BannerType type);
}
