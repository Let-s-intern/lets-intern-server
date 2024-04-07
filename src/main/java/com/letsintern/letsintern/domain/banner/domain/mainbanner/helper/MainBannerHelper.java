package com.letsintern.letsintern.domain.banner.domain.mainbanner.helper;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.domain.mainbanner.repository.MainBannerRepository;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainBannerHelper {

    private final MainBannerRepository mainBannerRepository;

    public MainBanner saveMainBanner(MainBanner mainBanner) {
        return mainBannerRepository.save(mainBanner);
    }

    public MainBanner findMainBannerById(Long bannerId) {
        return mainBannerRepository.findById(bannerId).orElseThrow(() -> BannerNotFound.EXCEPTION);
    }
}
