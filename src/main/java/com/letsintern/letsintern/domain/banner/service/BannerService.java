package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import com.letsintern.letsintern.domain.banner.dto.response.BannerAdminListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface BannerService {

    BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file);

    BannerAdminListResponse getBannerListForAdmin(Pageable pageable);

    BannerAdminResponse<?> getBannerForAdmin(Long id);

    void updateBanner(Long id, BannerUpdateDTO bannerUpdateDTO, MultipartFile file);

    void deleteBanner(Long bannerId);
}
