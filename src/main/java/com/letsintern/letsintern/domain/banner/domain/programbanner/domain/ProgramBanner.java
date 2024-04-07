package com.letsintern.letsintern.domain.banner.domain.programbanner.domain;

import com.letsintern.letsintern.domain.banner.domain.Banner;
import com.letsintern.letsintern.domain.banner.dto.request.BannerCreateDTO;
import com.letsintern.letsintern.domain.banner.dto.request.BannerUpdateDTO;
import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity(name = "ProgramBanner")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramBanner extends Banner {

    @NotNull
    private String imgUrl;

    @Builder(access = AccessLevel.PRIVATE)
    private ProgramBanner(BannerCreateDTO bannerCreateDTO, String s3Url) {
        super(bannerCreateDTO);
        this.imgUrl = s3Url;
    }
    public static ProgramBanner createProgramBanner(BannerCreateDTO bannerCreateDTO, String s3Url) {
        return ProgramBanner.builder()
                .bannerCreateDTO(bannerCreateDTO)
                .s3Url(s3Url)
                .build();
    }

    public void updateProgramBanner(BannerUpdateDTO bannerUpdateDTO, S3SavedFileVo s3SavedFileVo) {
        if(bannerUpdateDTO != null)
            super.updateBanner(bannerUpdateDTO);

        if(s3SavedFileVo != null)
            this.imgUrl = updateValue(this.imgUrl, s3SavedFileVo.getS3Url());
    }
}
