package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.Popup;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.helper.PopupHelper;
import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.maper.BannerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("POPUP")
@Transactional
@RequiredArgsConstructor
public class PopupServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;
    private final PopupHelper popupHelper;

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) {
        popupHelper.validatePopupCreateDTO(bannerCreateDTO);
        Popup newPopup = bannerMapper.toPopupEntity(bannerCreateDTO);
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
        popup.updatePopup(bannerUpdateDTO);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final Popup popup = popupHelper.findPopupById(bannerId);
        popupHelper.deletePopup(popup);
    }
}
