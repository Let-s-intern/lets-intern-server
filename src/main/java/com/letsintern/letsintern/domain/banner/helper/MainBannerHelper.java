package com.letsintern.letsintern.domain.banner.helper;

import com.letsintern.letsintern.domain.banner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.repository.MainBannerRepository;
import com.letsintern.letsintern.domain.banner.vo.MainBannerAdminVo;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<MainBannerAdminVo> getMainBannerAdminList(Pageable pageable) {
        return mainBannerRepository.findAllMainBannerAdminVos(pageable);
    }

    public void deleteMainBanner(MainBanner mainBanner) {
        mainBannerRepository.delete(mainBanner);
    }
}
