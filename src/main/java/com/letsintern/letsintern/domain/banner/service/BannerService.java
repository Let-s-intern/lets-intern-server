package com.letsintern.letsintern.domain.banner.service;

import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.response.BannerIdResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BannerService {

    BannerIdResponse createBanner(BannerCreateDTO bannerCreateDTO, MultipartFile file) throws IOException;

}
