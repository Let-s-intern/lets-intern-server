package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.ProgramBanner;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerListResponseDto;
import com.letsintern.letsintern.domain.banner.helper.ProgramBannerHelper;
import com.letsintern.letsintern.domain.banner.vo.ProgramBannerAdminVo;
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

import java.time.LocalDateTime;
import java.util.Objects;

@Service("PROGRAM")
@Transactional
@RequiredArgsConstructor
public class ProgramBannerServiceImpl implements BannerService {
    private final BannerMapper bannerMapper;
    private final ProgramBannerHelper programBannerHelper;
    private final S3Helper s3Helper;
    public static final String S3_PROGRAM_BANNER_DIR = "banner/program/";

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) {
        programBannerHelper.validateProgramBannerFileExists(file);
        S3SavedFileVo s3SavedFileVo = s3Helper.saveFile(file, S3_PROGRAM_BANNER_DIR);
        ProgramBanner newProgramBanner = bannerMapper.toProgramBannerEntity(bannerCreateDTO, s3SavedFileVo.getS3Url());
        programBannerHelper.saveProgramBanner(newProgramBanner);
        return bannerMapper.toBannerIdResponse(newProgramBanner.getId());
    }

    @Override
    public BannerAdminListResponse getBannerListForAdmin(Pageable pageable) {
        Page<ProgramBannerAdminVo> programBannerAdminVos = programBannerHelper.getProgramBannerAdminList(pageable);
        return bannerMapper.toBannerAdminListResponse(programBannerAdminVos);
    }

    @Override
    public BannerAdminResponse<?> getBannerForAdmin(Long id) {
        final ProgramBanner programBanner = programBannerHelper.findProgramBannerById(id);
        return bannerMapper.toBannerAdminResponse(programBanner);
    }

    @Override
    public void updateBanner(Long id, BannerUpdateDTO bannerUpdateDTO, MultipartFile file) {
        ProgramBanner programBanner = programBannerHelper.findProgramBannerById(id);
        S3SavedFileVo s3SavedFileVo = s3Helper.changeImgFile(S3_PROGRAM_BANNER_DIR, programBanner.getImgUrl(), file);
        Boolean isVisible = getIsVisibleForEndDateOrNull(bannerUpdateDTO.endDate());
        programBanner.updateProgramBanner(bannerUpdateDTO, s3SavedFileVo, isVisible);
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final ProgramBanner programBanner = programBannerHelper.findProgramBannerById(bannerId);
        s3Helper.deleteFile(S3_PROGRAM_BANNER_DIR + programBanner.getImgUrl().split("/")[5]);
        programBannerHelper.deleteProgramBanner(programBanner);
    }

    @Override
    public BannerListResponseDto<?> getBannerList(Pageable pageable) {
        return null;
    }

    private Boolean getIsVisibleForEndDateOrNull(LocalDateTime endDate) {
        if (Objects.isNull(endDate))
            return null;
        else
            return endDate.isAfter(LocalDateTime.now());
    }
}
