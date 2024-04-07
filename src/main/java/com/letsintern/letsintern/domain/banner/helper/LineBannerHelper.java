package com.letsintern.letsintern.domain.banner.helper;

import com.letsintern.letsintern.domain.banner.domain.LineBanner;
import com.letsintern.letsintern.domain.banner.exception.LineBannerCreateBadRequest;
import com.letsintern.letsintern.domain.banner.repository.LineBannerRepository;
import com.letsintern.letsintern.domain.banner.vo.LineBannerAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LineBannerHelper {

    private final LineBannerRepository lineBannerRepository;

    public void validateLineBannerCreateDTO(BannerCreateDTO bannerCreateDTO) {
        if(bannerCreateDTO.contents() == null || bannerCreateDTO.colorCode() == null || bannerCreateDTO.textColorCode() == null) {
            throw LineBannerCreateBadRequest.EXCEPTION;
        }
    }

    public LineBanner saveLineBanner(LineBanner lineBanner) {
        return lineBannerRepository.save(lineBanner);
    }

    public LineBanner findLineBannerById(Long bannerId) {
        return lineBannerRepository.findById(bannerId).orElseThrow(() -> BannerNotFound.EXCEPTION);
    }

    public Page<LineBannerAdminVo> getLineBannerAdminList(Pageable pageable) {
        return lineBannerRepository.findAllLineBannerAdminVos(pageable);
    }

    public void deleteLineBanner(LineBanner lineBanner) {
        lineBannerRepository.delete(lineBanner);
    }
}
