package com.letsintern.letsintern.domain.banner.domain.popup.helper;

import com.letsintern.letsintern.domain.banner.domain.popup.domain.Popup;
import com.letsintern.letsintern.domain.banner.domain.popup.exception.PopupCreateBadRequest;
import com.letsintern.letsintern.domain.banner.domain.popup.repository.PopupRepository;
import com.letsintern.letsintern.domain.banner.domain.popup.vo.PopupAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopupHelper {

    private final PopupRepository popupRepository;

    public void validatePopupCreateDTO(BannerCreateDTO bannerCreateDTO) {
        if(bannerCreateDTO.imgUrl() == null) {
            throw PopupCreateBadRequest.EXCEPTION;
        }
    }

    public Popup savePopup(Popup popup) {
        return popupRepository.save(popup);
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
}
