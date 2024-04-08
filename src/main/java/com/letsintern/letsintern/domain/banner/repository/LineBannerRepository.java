package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.domain.LineBanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineBannerRepository extends JpaRepository<LineBanner, Long>, LineBannerRepositoryCustom {

    Optional<LineBanner> findById(Long id);

}
