package com.letsintern.letsintern.domain.banner.domain.linebanner;

import com.letsintern.letsintern.domain.banner.domain.linebanner.response.LineBannerListResponse;
import com.letsintern.letsintern.domain.banner.domain.linebanner.service.LineBannerService;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banner/line")
@RequiredArgsConstructor
@Tag(name = "LineBanner")
public class LineBannerController {

    public final LineBannerService lineBannerService;

    @PostMapping
    public BannerIdResponse createLineBannerForAdmin(@RequestBody @Valid final BannerCreateDTO bannerCreateDTO) {
        return lineBannerService.createBanner(bannerCreateDTO, null);
    }

    @GetMapping("/admin")
    public LineBannerListResponse getLineBannerListForAdmin(@PageableDefault Pageable pageable) {
        return lineBannerService.getLineBannerListForAdmin(pageable);
    }

    @PatchMapping("/{id}")
    public void updateLineBannerForAdmin(@PathVariable final Long id,
                                         @RequestBody @Nullable final BannerUpdateDTO bannerUpdateDTO) {
        lineBannerService.updateLineBanner(id, bannerUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLineBannerForAdmin(@PathVariable final Long id) {
        lineBannerService.deleteBanner(id);
    }
}
