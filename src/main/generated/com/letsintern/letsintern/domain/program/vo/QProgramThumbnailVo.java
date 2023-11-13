package com.letsintern.letsintern.domain.program.vo;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.letsintern.letsintern.domain.program.vo.QProgramThumbnailVo is a Querydsl Projection type for ProgramThumbnailVo
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProgramThumbnailVo extends ConstructorExpression<ProgramThumbnailVo> {

    private static final long serialVersionUID = -861108337L;

    public QProgramThumbnailVo(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<com.letsintern.letsintern.domain.program.domain.ProgramType> type, com.querydsl.core.types.Expression<Integer> th, com.querydsl.core.types.Expression<? extends java.util.Date> dueDate, com.querydsl.core.types.Expression<String> startDate) {
        super(ProgramThumbnailVo.class, new Class<?>[]{long.class, com.letsintern.letsintern.domain.program.domain.ProgramType.class, int.class, java.util.Date.class, String.class}, id, type, th, dueDate, startDate);
    }

}

