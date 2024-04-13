package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.LineBanner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerListResponseDto;
import com.letsintern.letsintern.domain.banner.helper.LineBannerHelper;
import com.letsintern.letsintern.domain.banner.maper.BannerMapper;
import com.letsintern.letsintern.domain.banner.vo.LineBannerAdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

@Service("LINE")
@Transactional
@RequiredArgsConstructor
public class LineBannerServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;
    private final LineBannerHelper lineBannerHelper;

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) {
        lineBannerHelper.validateLineBannerCreateDTO(bannerCreateDTO);
        LineBanner newLineBanner = bannerMapper.toLineBannerEntity(bannerCreateDTO);
        lineBannerHelper.saveLineBanner(newLineBanner);
        return bannerMapper.toBannerIdResponse(newLineBanner.getId());
    }

    @Override
    public BannerAdminListResponse getBannerListForAdmin(Pageable pageable) {
        Page<LineBannerAdminVo> lineBannerAdminVos = lineBannerHelper.getLineBannerAdminList(pageable);
        return bannerMapper.toBannerAdminListResponse(lineBannerAdminVos);
    }

    @Override
    public void updateBanner(Long id, BannerUpdateDTO bannerUpdateDTO, MultipartFile file) {
        LineBanner lineBanner = lineBannerHelper.findLineBannerById(id);
        Boolean isVisible = getIsVisibleForEndDateOrNull(bannerUpdateDTO.endDate());
        lineBanner.updateLineBanner(bannerUpdateDTO, isVisible);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final LineBanner lineBanner = lineBannerHelper.findLineBannerById(bannerId);
        lineBannerHelper.deleteLineBanner(lineBanner);
    }

    @Override
    public BannerListResponseDto<?> getBannerList(Pageable pageable) {
        return null;
    }

    private Boolean getIsVisibleForEndDateOrNull(LocalDateTime endDate) {
        if (Objects.isNull(endDate))
            return null;
        else
            return endDate.isAfter(LocalDateTime.now());
    }
}
