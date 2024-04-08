package com.letsintern.letsintern.domain.banner.helper;

import com.letsintern.letsintern.domain.banner.domain.Popup;
import com.letsintern.letsintern.domain.banner.exception.PopupCreateBadRequest;
import com.letsintern.letsintern.domain.banner.repository.PopupRepository;
import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
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
        if (bannerCreateDTO.imgUrl() == null) {
            throw PopupCreateBadRequest.EXCEPTION;
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
}
