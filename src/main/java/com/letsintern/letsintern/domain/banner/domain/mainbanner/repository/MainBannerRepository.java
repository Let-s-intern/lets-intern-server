package com.letsintern.letsintern.domain.banner.domain.mainbanner.repository;

import com.letsintern.letsintern.domain.banner.domain.mainbanner.domain.MainBanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainBannerRepository extends JpaRepository<MainBanner, Long>, MainBannerRepositoryCustom {

    Optional<MainBanner> findById(Long id);

}
