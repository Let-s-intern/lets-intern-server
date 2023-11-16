package com.letsintern.letsintern.domain.program.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProgram is a Querydsl query type for Program
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProgram extends EntityPathBase<Program> {

    private static final long serialVersionUID = 1077001953L;

    public static final QProgram program = new QProgram("program");

    public final StringPath announcementDate = createString("announcementDate");

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final ListPath<com.letsintern.letsintern.domain.faq.domain.Faq, com.letsintern.letsintern.domain.faq.domain.QFaq> faqList = this.<com.letsintern.letsintern.domain.faq.domain.Faq, com.letsintern.letsintern.domain.faq.domain.QFaq>createList("faqList", com.letsintern.letsintern.domain.faq.domain.Faq.class, com.letsintern.letsintern.domain.faq.domain.QFaq.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isApproved = createBoolean("isApproved");

    public final BooleanPath isVisible = createBoolean("isVisible");

    public final StringPath link = createString("link");

    public final StringPath location = createString("location");

    public final StringPath startDate = createString("startDate");

    public final EnumPath<ProgramStatus> status = createEnum("status", ProgramStatus.class);

    public final NumberPath<Integer> th = createNumber("th", Integer.class);

    public final StringPath title = createString("title");

    public final EnumPath<ProgramType> type = createEnum("type", ProgramType.class);

    public final EnumPath<ProgramWay> way = createEnum("way", ProgramWay.class);

    public QProgram(String variable) {
        super(Program.class, forVariable(variable));
    }

    public QProgram(Path<? extends Program> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProgram(PathMetadata metadata) {
        super(Program.class, metadata);
    }

}

