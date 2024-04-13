package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.Popup;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerListResponseDto;
import com.letsintern.letsintern.domain.banner.helper.PopupHelper;
import com.letsintern.letsintern.domain.banner.vo.BannerVo;
import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.maper.BannerMapper;
import com.letsintern.letsintern.domain.file.helper.S3Helper;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;

@Service("POPUP")
@Transactional
@RequiredArgsConstructor
public class PopupServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;
    private final PopupHelper popupHelper;
    private final S3Helper s3Helper;
    public static final String S3_POPUP_DIR = "banner/popup/";

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) {
        popupHelper.validatePopupFileExists(file);
        S3SavedFileVo s3SavedFileVo = s3Helper.saveFile(file, S3_POPUP_DIR);
        Popup newPopup = bannerMapper.toPopupEntity(bannerCreateDTO, s3SavedFileVo.getS3Url());
        popupHelper.savePopup(newPopup);
        return bannerMapper.toBannerIdResponse(newPopup.getId());
    }

    @Override
    public BannerAdminListResponse getBannerListForAdmin(Pageable pageable) {
        Page<PopupAdminVo> popupAdminVos = popupHelper.getPopupAdminList(pageable);
        return bannerMapper.toBannerAdminListResponse(popupAdminVos);
    }

    @Override
    public void updateBanner(Long id, BannerUpdateDTO bannerUpdateDTO, MultipartFile file) {
        Popup popup = popupHelper.findPopupById(id);
        S3SavedFileVo s3SavedFileVo = s3Helper.changeImgFile(S3_POPUP_DIR, popup.getImgUrl(), file);
        Boolean isVisible = getIsVisibleForEndDateOrNull(bannerUpdateDTO.endDate());
        popup.updatePopup(bannerUpdateDTO, s3SavedFileVo, isVisible);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final Popup popup = popupHelper.findPopupById(bannerId);
        popupHelper.deletePopup(popup);
    }

    @Override
    public BannerListResponseDto<?> getBannerList(Pageable pageable) {
        Page<BannerVo> bannerVoPage = popupHelper.findBannerList(pageable);
        return bannerMapper.toBannerListResponseDto(bannerVoPage);
    }

    private Boolean getIsVisibleForEndDateOrNull(LocalDateTime endDate) {
        if (Objects.isNull(endDate))
            return null;
        else
            return endDate.isAfter(LocalDateTime.now());
    }
}
