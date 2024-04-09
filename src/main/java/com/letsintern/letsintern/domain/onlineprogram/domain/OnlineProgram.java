package com.letsintern.letsintern.domain.onlineprogram.domain;

import com.letsintern.letsintern.domain.file.vo.S3SavedFileVo;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramCreateDTO;
import com.letsintern.letsintern.domain.onlineprogram.dto.request.OnlineProgramUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;


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

    public void updateOnlineProgram(OnlineProgramUpdateDTO onlineProgramUpdateDTO, S3SavedFileVo s3SavedFileVo) {
        if(onlineProgramUpdateDTO != null) {
            this.title = updateValue(this.title, onlineProgramUpdateDTO.title());
            this.description = updateValue(this.description, onlineProgramUpdateDTO.description());
            this.link = updateValue(this.link, onlineProgramUpdateDTO.link());
        }

        if(s3SavedFileVo != null) {
            this.thumbnailUrl = updateValue(this.thumbnailUrl, s3SavedFileVo.getS3Url());
        }
    }
}
