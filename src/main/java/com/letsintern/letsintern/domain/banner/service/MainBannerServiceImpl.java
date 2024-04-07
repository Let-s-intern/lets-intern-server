package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.MainBanner;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.helper.MainBannerHelper;
import com.letsintern.letsintern.domain.banner.vo.MainBannerAdminVo;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.maper.BannerMapper;
import com.letsintern.letsintern.domain.file.helper.S3Helper;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("MAIN")
@Transactional
@RequiredArgsConstructor
public class MainBannerServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;
    private final MainBannerHelper mainBannerHelper;
    private final S3Helper s3Helper;
    public static final String S3_MAIN_BANNER_DIR = "banner/main/";

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) throws IOException {
        mainBannerHelper.validateMainBannerFileExists(file);
        S3SavedFileVo s3SavedFileVo = s3Helper.saveFile(file, S3_MAIN_BANNER_DIR);
        MainBanner newMainBanner = bannerMapper.toMainBannerEntity(bannerCreateDTO, s3SavedFileVo.getS3Url());
        mainBannerHelper.saveMainBanner(newMainBanner);
        return bannerMapper.toBannerIdResponse(newMainBanner.getId());
    }

    @Override
    public BannerAdminListResponse getBannerListForAdmin(Pageable pageable) {
        Page<MainBannerAdminVo> mainBannerAdminVos = mainBannerHelper.getMainBannerAdminList(pageable);
        return bannerMapper.toBannerAdminListResponse(mainBannerAdminVos);
    }

    @Override
    public void updateBanner(Long id, BannerUpdateDTO bannerUpdateDTO, MultipartFile file) throws IOException {
        MainBanner mainBanner = mainBannerHelper.findMainBannerById(id);
        S3SavedFileVo s3SavedFileVo = s3Helper.changeBannerImgFile(S3_MAIN_BANNER_DIR, mainBanner.getImgUrl(), file);
        mainBanner.updateMainBanner(bannerUpdateDTO, s3SavedFileVo);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final MainBanner mainBanner = mainBannerHelper.findMainBannerById(bannerId);
        s3Helper.deleteFile(S3_MAIN_BANNER_DIR + mainBanner.getImgUrl().split("/")[5]);
        mainBannerHelper.deleteMainBanner(mainBanner);
    }
}
