package com.letsintern.letsintern.domain.contents.domain;

import com.letsintern.letsintern.domain.contents.dto.request.ContentsCreateDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contents {

    @Id
    @Column(name = "contents_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentsType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentsTopic topic;

    @NotNull
    private String link;

    @NotNull
    private Boolean isVisible = false;

    @NotNull
    private LocalDate createdAt;

    @Builder
    private Contents(String title, ContentsType type, ContentsTopic topic, String link) {
        this.title = title;
        this.type = type;
        this.topic = topic;
        this.link = link;
        this.createdAt = LocalDate.now();
    }

    public static Contents from(ContentsCreateDTO contentsCreateDTO) {
        return Contents.builder()
                .title(contentsCreateDTO.getTitle())
                .type(contentsCreateDTO.getType())
                .topic(contentsCreateDTO.getTopic())
                .link(contentsCreateDTO.getLink())
                .build();
    }
}
