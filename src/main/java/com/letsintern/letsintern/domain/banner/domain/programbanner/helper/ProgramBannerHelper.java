package com.letsintern.letsintern.domain.banner.domain.programbanner.helper;

import com.letsintern.letsintern.domain.banner.domain.programbanner.domain.ProgramBanner;
import com.letsintern.letsintern.domain.banner.domain.programbanner.repository.ProgramBannerRepository;
import com.letsintern.letsintern.domain.banner.domain.programbanner.vo.ProgramBannerAdminVo;
import com.letsintern.letsintern.domain.banner.exception.BannerNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgramBannerHelper {

    private final ProgramBannerRepository programBannerRepository;

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
