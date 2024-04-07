package com.letsintern.letsintern.domain.banner.domain.linebanner.service;

import com.letsintern.letsintern.domain.banner.domain.linebanner.domain.LineBanner;
import com.letsintern.letsintern.domain.banner.domain.linebanner.helper.LineBannerHelper;
import com.letsintern.letsintern.domain.banner.domain.linebanner.maper.LineBannerMapper;
import com.letsintern.letsintern.domain.banner.domain.linebanner.response.LineBannerListResponse;
import com.letsintern.letsintern.domain.banner.domain.linebanner.vo.LineBannerAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.maper.BannerMapper;
import com.letsintern.letsintern.domain.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class LineBannerService implements BannerService {
    private final BannerMapper bannerMapper;
    private final LineBannerMapper lineBannerMapper;
    private final LineBannerHelper lineBannerHelper;

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) {
        lineBannerHelper.validateLineBannerCreateDTO(bannerCreateDTO);
        LineBanner newLineBanner = lineBannerMapper.toEntity(bannerCreateDTO);
        lineBannerHelper.saveLineBanner(newLineBanner);
        return bannerMapper.toBannerIdResponse(newLineBanner.getId());
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final LineBanner lineBanner = lineBannerHelper.findLineBannerById(bannerId);
        lineBannerHelper.deleteLineBanner(lineBanner);
    }

    public LineBannerListResponse getLineBannerListForAdmin(Pageable pageable) {
        Page<LineBannerAdminVo> lineBannerAdminVos = lineBannerHelper.getLineBannerAdminList(pageable);
        return lineBannerMapper.toLineBannerListResponse(lineBannerAdminVos);
    }

    public void updateLineBanner(Long id, BannerUpdateDTO bannerUpdateDTO) {
        LineBanner lineBanner = lineBannerHelper.findLineBannerById(id);
        lineBanner.updateLineBanner(bannerUpdateDTO);
    }

}
