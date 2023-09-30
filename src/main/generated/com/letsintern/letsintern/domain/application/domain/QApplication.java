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

    public final BooleanPath checkAttendance = createBoolean("checkAttendance");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.letsintern.letsintern.domain.program.domain.QProgram program;

    public final StringPath question = createString("question");

    public final com.letsintern.letsintern.domain.user.domain.QUser user;

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
        this.user = inits.isInitialized("user") ? new com.letsintern.letsintern.domain.user.domain.QUser(forProperty("user")) : null;
    }

}
