package com.letsintern.letsintern.domain.banner.domain.popup;

import com.letsintern.letsintern.domain.banner.domain.popup.response.PopupListResponse;
import com.letsintern.letsintern.domain.banner.domain.popup.service.PopupService;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banner/popup")
@RequiredArgsConstructor
@Tag(name = "Popup")
public class PopupController {

    public final PopupService popupService;

    @PostMapping
    public BannerIdResponse createPopupForAdmin(@RequestBody @Valid final BannerCreateDTO bannerCreateDTO) {
        return popupService.createBanner(bannerCreateDTO, null);
    }

    @GetMapping("/admin")
    public PopupListResponse getPopupListForAdmin(@PageableDefault Pageable pageable) {
        return popupService.getPopupListForAdmin(pageable);
    }

    @PatchMapping("/{id}")
    public void updatePopupForAdmin(@PathVariable final Long id,
                                    @RequestBody final BannerUpdateDTO bannerUpdateDTO) {
        popupService.updatePopup(id, bannerUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePopupForAdmin(@PathVariable final Long id) {
        popupService.deleteBanner(id);
    }
}
