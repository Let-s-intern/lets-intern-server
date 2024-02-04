package com.letsintern.letsintern.domain.file.domain;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String s3Url;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentsTopic contentsTopic;

    @NotNull
    private LocalDate createdAt;

    @Builder
    private File(String s3Url, ContentsTopic contentsTopic) {
        this.s3Url = s3Url;
        this.contentsTopic = contentsTopic;
        this.createdAt = LocalDate.now();
    }

    public static File of(String s3Url, ContentsTopic contentsTopic) {
        return File.builder()
                .s3Url(s3Url)
                .contentsTopic(contentsTopic)
                .build();
    }
}
