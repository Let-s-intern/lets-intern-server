package com.letsintern.letsintern.domain.banner.domain.popup.service;

import com.letsintern.letsintern.domain.banner.domain.popup.domain.Popup;
import com.letsintern.letsintern.domain.banner.domain.popup.helper.PopupHelper;
import com.letsintern.letsintern.domain.banner.domain.popup.maper.PopupMapper;
import com.letsintern.letsintern.domain.banner.domain.popup.response.PopupListResponse;
import com.letsintern.letsintern.domain.banner.domain.popup.vo.PopupAdminVo;
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
public class PopupService implements BannerService {
    private final BannerMapper bannerMapper;
    private final PopupMapper popupMapper;
    private final PopupHelper popupHelper;

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) {
        popupHelper.validatePopupCreateDTO(bannerCreateDTO);
        Popup newPopup = popupMapper.toEntity(bannerCreateDTO);
        popupHelper.savePopup(newPopup);
        return bannerMapper.toBannerIdResponse(newPopup.getId());
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final Popup popup = popupHelper.findPopupById(bannerId);
        popupHelper.deletePopup(popup);
    }

    public PopupListResponse getPopupListForAdmin(Pageable pageable) {
        Page<PopupAdminVo> popupAdminVos = popupHelper.getPopupAdminList(pageable);
        return popupMapper.toPopupListResponse(popupAdminVos);
    }

    public void updatePopup(Long id, BannerUpdateDTO bannerUpdateDTO) {
        Popup popup = popupHelper.findPopupById(id);
        popup.updatePopup(bannerUpdateDTO);
    }
}
