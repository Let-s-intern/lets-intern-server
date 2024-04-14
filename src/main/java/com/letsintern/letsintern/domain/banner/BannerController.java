package com.letsintern.letsintern.domain.banner;

import com.letsintern.letsintern.domain.banner.domain.BannerType;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerListResponseDto;
import com.letsintern.letsintern.domain.banner.service.BannerServiceFactory;
import com.letsintern.letsintern.domain.banner.service.BannerSpecificService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banner")
@Tag(name = "Banner")
public class BannerController {

    private final BannerServiceFactory bannerServiceFactory;
    private final BannerSpecificService bannerSpecificService;

    @PostMapping
    @Operation(summary = "어드민 배너 1개 생성")
    public BannerIdResponse createBannerForAdmin(@RequestPart @Valid final BannerCreateDTO bannerCreateDTO,
                                                 @RequestPart(required = false) MultipartFile file) {
        return bannerServiceFactory.getBannerService(bannerCreateDTO.type()).createBanner(bannerCreateDTO, file);
    }

    @GetMapping("/admin")
    @Operation(summary = "어드민 배너 타입별 전체 목록")
    public BannerAdminListResponse getBannerListForAdmin(@RequestParam BannerType type,
                                                         @PageableDefault Pageable pageable) {
        return bannerServiceFactory.getBannerService(type).getBannerListForAdmin(pageable);
    }

    @GetMapping("/admin/{id}")
    @Operation(summary = "어드민 배너 1건 상세 보기")
    public ResponseEntity<?> getBannerForAdmin(@RequestParam BannerType type,
                                               @PathVariable final Long id) {
        return ResponseEntity.ok(bannerServiceFactory.getBannerService(type).getBannerForAdmin(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "어드민 배너 1건 수정")
    public void updateBannerForAdmin(@RequestParam BannerType type,
                                     @PathVariable final Long id,
                                     @RequestPart(required = false) final BannerUpdateDTO bannerUpdateDTO,
                                     @RequestPart(required = false) MultipartFile file) {
        bannerServiceFactory.getBannerService(type).updateBanner(id, bannerUpdateDTO, file);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "어드민 배너 1건 삭제")
    public void deleteLineBannerForAdmin(@RequestParam @NotNull BannerType type,
                                         @PathVariable final Long id) {
        bannerServiceFactory.getBannerService(type).deleteBanner(id);
    }

    @GetMapping
    @Operation(summary = "타입별 유효한 배너 목록")
    public ResponseEntity<BannerListResponseDto<?>> getBannerList(@RequestParam(name = "type") BannerType bannerType,
                                                                 @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(bannerServiceFactory.getBannerService(bannerType).getBannerList(pageable));
    }

}
