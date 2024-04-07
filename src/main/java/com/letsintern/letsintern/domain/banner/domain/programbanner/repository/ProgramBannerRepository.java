package com.letsintern.letsintern.domain.banner.domain.programbanner.repository;

import com.letsintern.letsintern.domain.banner.domain.programbanner.domain.ProgramBanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramBannerRepository extends JpaRepository<ProgramBanner, Long>, ProgramBannerRepositoryCustom {

    Optional<ProgramBanner> findById(Long id);

}
