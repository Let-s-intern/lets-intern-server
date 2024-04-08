package com.letsintern.letsintern.domain.banner.helper;

import com.letsintern.letsintern.domain.banner.domain.ProgramBanner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.exception.MainOrProgramBannerCreateBadRequest;
import com.letsintern.letsintern.domain.banner.repository.ProgramBannerRepository;
import com.letsintern.letsintern.domain.banner.vo.ProgramBannerAdminVo;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class ProgramBannerHelper {

    private final ProgramBannerRepository programBannerRepository;

    public void validateProgramBannerFileExists(MultipartFile file) {
        if (file == null) {
            throw MainOrProgramBannerCreateBadRequest.EXCEPTION;
        }
    }

    public ProgramBanner saveProgramBanner(ProgramBanner programBanner) {
        return programBannerRepository.save(programBanner);
    }

    public ProgramBanner findProgramBannerById(Long bannerId) {
        return programBannerRepository.findById(bannerId).orElseThrow(() -> BannerNotFound.EXCEPTION);
    }

    public Page<ProgramBannerAdminVo> getProgramBannerAdminList(Pageable pageable) {
        return programBannerRepository.findAllProgramBannerAdminVos(pageable);
    }

    public void deleteProgramBanner(ProgramBanner programBanner) {
        programBannerRepository.delete(programBanner);
    }
}
