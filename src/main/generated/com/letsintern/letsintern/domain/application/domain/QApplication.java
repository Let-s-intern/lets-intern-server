package com.letsintern.letsintern.domain.application.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplication is a Querydsl query type for Application
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplication extends EntityPathBase<Application> {

    private static final long serialVersionUID = 1821562465L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplication application = new QApplication("application");

    public final StringPath applyMotive = createString("applyMotive");

    public final BooleanPath attendance = createBoolean("attendance");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<InflowPath> inflowPath = createEnum("inflowPath", InflowPath.class);

    public final BooleanPath isApproved = createBoolean("isApproved");

    public final StringPath preQuestions = createString("preQuestions");

    public final com.letsintern.letsintern.domain.program.domain.QProgram program;

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public final EnumPath<ApplicationStatus> status = createEnum("status", ApplicationStatus.class);

    public final StringPath wishCompany = createString("wishCompany");

    public final StringPath wishJob = createString("wishJob");

    public QApplication(String variable) {
        this(Application.class, forVariable(variable), INITS);
    }

    public QApplication(Path<? extends Application> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplication(PathMetadata metadata, PathInits inits) {
        this(Application.class, metadata, inits);
    }

    public QApplication(Class<? extends Application> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new com.letsintern.letsintern.domain.program.domain.QProgram(forProperty("program")) : null;
    }

}

