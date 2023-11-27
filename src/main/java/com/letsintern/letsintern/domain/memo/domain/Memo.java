package com.letsintern.letsintern.domain.memo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letsintern.letsintern.domain.memo.dto.request.MemoDTO;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    @Id
    @Column(name = "memo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String contents;

    @NotNull
    String creator;

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    String createdAt;

    @NotNull
    Long targetUserId;

    @Builder
    private Memo(String contents, String creator, Long targetUserId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.contents = contents;
        this.creator = creator;
        this.createdAt = simpleDateFormat.format(new Date());
        this.targetUserId = targetUserId;
    }

    public static Memo of(MemoDTO memoDTO, String creator, Long targetUserId) {
        return Memo.builder()
                .contents(memoDTO.getContents())
                .creator(creator)
                .targetUserId(targetUserId)
                .build();
    }
}
