package com.letsintern.letsintern.domain.onlineprogram.domain;

import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "OnlineProgram")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OnlineProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_program_id")
    private Long id;

    private String title;

    private String description;

    private String link;

    private String thumbnailUrl;

    public static OnlineProgram createOnlineProgram(OnlineProgramCreateDTO onlineProgramCreateDTO, String s3Url) {
        return OnlineProgram.builder()
                .title(onlineProgramCreateDTO.title())
                .description(onlineProgramCreateDTO.description())
                .link(onlineProgramCreateDTO.link())
                .thumbnailUrl(s3Url)
                .build();
    }
}
