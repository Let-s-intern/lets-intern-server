package com.letsintern.letsintern.domain.banner.domain.mainbanner;

import com.letsintern.letsintern.domain.banner.domain.Banner;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainBanner extends Banner {

    @NotNull
    private String imgUrl;

}
