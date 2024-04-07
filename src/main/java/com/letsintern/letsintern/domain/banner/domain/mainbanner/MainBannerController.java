package com.letsintern.letsintern.domain.banner.domain.mainbanner;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.service.MainBannerService;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
    public BannerIdResponse createMainBannerForAdmin(@RequestPart final BannerCreateDTO bannerCreateDTO,
                                                     @RequestPart("file") @NotNull MultipartFile file) throws IOException {
        return mainBannerService.createBanner(bannerCreateDTO, file);
    }
}
