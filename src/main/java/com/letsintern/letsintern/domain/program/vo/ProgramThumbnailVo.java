package com.letsintern.letsintern.domain.program.vo;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class ProgramThumbnailVo {

    private Long id;

    @Enumerated(EnumType.STRING)
    private ProgramType type;

    private Integer th;

    private String dueDate;

    private String startDate;

    @QueryProjection
    public ProgramThumbnailVo(Long id, ProgramType type, Integer th, Date dueDate, String startDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.id = id;
        this.type = type;
        this.th = th;
        this.dueDate = simpleDateFormat.format(dueDate);
        this.startDate = startDate;
    }
}
