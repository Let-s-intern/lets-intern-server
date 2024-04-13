package com.letsintern.letsintern.domain.banner.helper;

import com.letsintern.letsintern.domain.banner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.exception.BannerCreateNoFileBadRequest;
import com.letsintern.letsintern.domain.banner.repository.MainBannerRepository;
import com.letsintern.letsintern.domain.banner.vo.BannerVo;
import com.letsintern.letsintern.domain.banner.vo.MainBannerAdminVo;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class MainBannerHelper {

    private final MainBannerRepository mainBannerRepository;

    public void validateMainBannerFileExists(MultipartFile file) {
        if (file == null) {
            throw BannerCreateNoFileBadRequest.EXCEPTION;
        }
    }

    public void saveMainBanner(MainBanner mainBanner) {
        mainBannerRepository.save(mainBanner);
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

    public Page<BannerVo> findBannerList(Pageable pageable) {
        return mainBannerRepository.findValidAndVisibleBanner(pageable);
    }
}
