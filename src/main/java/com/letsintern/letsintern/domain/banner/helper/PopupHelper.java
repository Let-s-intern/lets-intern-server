package com.letsintern.letsintern.domain.banner.helper;

import com.letsintern.letsintern.domain.banner.domain.Popup;
import com.letsintern.letsintern.domain.banner.exception.BannerCreateNoFileBadRequest;
import com.letsintern.letsintern.domain.banner.repository.PopupRepository;
import com.letsintern.letsintern.domain.banner.vo.BannerVo;
import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class PopupHelper {

    private final PopupRepository popupRepository;

    public void validatePopupFileExists(MultipartFile file) {
        if (file == null) {
            throw BannerCreateNoFileBadRequest.EXCEPTION;
        }
    }

    public void savePopup(Popup popup) {
        popupRepository.save(popup);
    }

    public Popup findPopupById(Long bannerId) {
        return popupRepository.findById(bannerId).orElseThrow(() -> BannerNotFound.EXCEPTION);
    }

    public Page<PopupAdminVo> getPopupAdminList(Pageable pageable) {
        return popupRepository.findAllPopupAdminVos(pageable);
    }

    public void deletePopup(Popup popup) {
        popupRepository.delete(popup);
    }

    public Page<BannerVo> findBannerList(Pageable pageable) {
        return popupRepository.findValidAndVisibleBanner(pageable);
    }
}
