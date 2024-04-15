package com.letsintern.letsintern.domain.banner;

import com.letsintern.letsintern.domain.banner.domain.BannerType;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.service.BannerServiceFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banner")
@Tag(name = "Banner")
public class BannerController {

    private final BannerServiceFactory bannerServiceFactory;

    @PostMapping
    public BannerIdResponse createBannerForAdmin(@RequestPart @Valid final BannerCreateDTO bannerCreateDTO,
                                                 @RequestPart(required = false) MultipartFile file) {
        return bannerServiceFactory.getBannerService(bannerCreateDTO.type()).createBanner(bannerCreateDTO, file);
    }

    @GetMapping("/admin")
    public BannerAdminListResponse getBannerListForAdmin(@RequestParam BannerType type,
                                                         @PageableDefault Pageable pageable) {
        return bannerServiceFactory.getBannerService(type).getBannerListForAdmin(pageable);
    }

    @PatchMapping("/{id}")
    public void updateBannerForAdmin(@RequestParam BannerType type,
                                     @PathVariable final Long id,
                                     @RequestPart(required = false) final BannerUpdateDTO bannerUpdateDTO,
                                     @RequestPart(required = false) MultipartFile file) {
        bannerServiceFactory.getBannerService(type).updateBanner(id, bannerUpdateDTO, file);
    }

    @DeleteMapping("/{id}")
    public void deleteLineBannerForAdmin(@RequestParam @NotNull BannerType type,
                                         @PathVariable final Long id) {
        bannerServiceFactory.getBannerService(type).deleteBanner(id);
    }
}
