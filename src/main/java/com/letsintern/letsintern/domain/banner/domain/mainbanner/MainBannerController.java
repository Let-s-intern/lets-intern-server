package com.letsintern.letsintern.domain.banner.domain.mainbanner;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.dto.response.MainBannerListResponse;
import com.letsintern.letsintern.domain.banner.domain.mainbanner.service.MainBannerService;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/banner/main")
@RequiredArgsConstructor
@Tag(name = "MainBanner")
public class MainBannerController {

    public final MainBannerService mainBannerService;

    @PostMapping
    public BannerIdResponse createMainBannerForAdmin(@RequestPart @Valid final BannerCreateDTO bannerCreateDTO,
                                                     @RequestPart("file") @NotNull MultipartFile file) throws IOException {
        return mainBannerService.createBanner(bannerCreateDTO, file);
    }

    @GetMapping("/admin")
    public MainBannerListResponse getMainBannerListForAdmin(@PageableDefault Pageable pageable) {
        return mainBannerService.getMainBannerListForAdmin(pageable);
    }

    @PatchMapping("/{id}")
    public void updateMainBannerListForAdmin(@PathVariable final Long id,
                                             @RequestPart @Nullable final BannerUpdateDTO bannerUpdateDTO,
                                             @RequestPart("file") @Nullable MultipartFile file) throws IOException {
        mainBannerService.updateMainBanner(id, bannerUpdateDTO, file);
    }

    @DeleteMapping("/{id}")
    public void deleteMainBannerForAdmin(@PathVariable final Long id) {
        mainBannerService.deleteBanner(id);
    }
}
