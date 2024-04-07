package com.letsintern.letsintern.domain.banner.domain.programbanner;

import com.letsintern.letsintern.domain.banner.domain.programbanner.response.ProgramBannerListResponse;
import com.letsintern.letsintern.domain.banner.domain.programbanner.service.ProgramBannerService;
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
@RequestMapping("/banner/program")
@RequiredArgsConstructor
@Tag(name = "ProgramBanner")
public class ProgramBannerController {

    public final ProgramBannerService programBannerService;

    @PostMapping
    public BannerIdResponse createProgramBannerForAdmin(@RequestPart @Valid final BannerCreateDTO bannerCreateDTO,
                                                        @RequestPart("file") @NotNull MultipartFile file) throws IOException {
        return programBannerService.createBanner(bannerCreateDTO, file);
    }

    @GetMapping("/admin")
    public ProgramBannerListResponse getProgramBannerListForAdmin(@PageableDefault Pageable pageable) {
        return programBannerService.getProgramBannerListForAdmin(pageable);
    }

    @PatchMapping("/{id}")
    public void updateProgramBannerForAdmin(@PathVariable final Long id,
                                            @RequestPart @Nullable final BannerUpdateDTO bannerUpdateDTO,
                                            @RequestPart("file") @Nullable MultipartFile file) throws IOException {
        programBannerService.updateProgramBanner(id, bannerUpdateDTO, file);
    }

    @DeleteMapping("/{id}")
    public void deleteProgramBannerForAdmin(@PathVariable final Long id) {
        programBannerService.deleteBanner(id);
    }
}
