package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.domain.ProgramBanner;
import com.letsintern.letsintern.domain.banner.helper.ProgramBannerHelper;
import com.letsintern.letsintern.domain.banner.maper.ProgramBannerMapper;
import com.letsintern.letsintern.domain.banner.dto.response.ProgramBannerListResponse;
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

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramBannerService implements BannerService {
    private final BannerMapper bannerMapper;
    private final ProgramBannerMapper programBannerMapper;
    private final ProgramBannerHelper programBannerHelper;
    private final S3Helper s3Helper;
    public static final String S3_PROGRAM_BANNER_DIR = "banner/program/";

    @Override
    public BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) throws IOException {
        S3SavedFileVo s3SavedFileVo = s3Helper.saveFile(file, S3_PROGRAM_BANNER_DIR);
        ProgramBanner newProgramBanner = programBannerMapper.toEntity(bannerCreateDTO, s3SavedFileVo.getS3Url());
        programBannerHelper.saveProgramBanner(newProgramBanner);
        return bannerMapper.toBannerIdResponse(newProgramBanner.getId());
    }

    @Override
    public void deleteBanner(Long bannerId) {
        final ProgramBanner programBanner = programBannerHelper.findProgramBannerById(bannerId);
        s3Helper.deleteFile(S3_PROGRAM_BANNER_DIR + programBanner.getImgUrl().split("/")[5]);
        programBannerHelper.deleteProgramBanner(programBanner);
    }

    public ProgramBannerListResponse getProgramBannerListForAdmin(Pageable pageable) {
        Page<ProgramBannerAdminVo> programBannerAdminVos = programBannerHelper.getProgramBannerAdminList(pageable);
        return programBannerMapper.toProgramBannerListResponse(programBannerAdminVos);
    }

    public void updateProgramBanner(Long id, BannerUpdateDTO bannerUpdateDTO, MultipartFile file) throws IOException {
        ProgramBanner programBanner = programBannerHelper.findProgramBannerById(id);
        S3SavedFileVo s3SavedFileVo = s3Helper.changeBannerImgFile(S3_PROGRAM_BANNER_DIR, programBanner.getImgUrl(), file);
        programBanner.updateProgramBanner(bannerUpdateDTO, s3SavedFileVo);
    }


}
