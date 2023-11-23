package com.letsintern.letsintern.domain.application.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserApplication is a Querydsl query type for UserApplication
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserApplication extends EntityPathBase<UserApplication> {

    private static final long serialVersionUID = -1177426666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserApplication userApplication = new QUserApplication("userApplication");

    public final QApplication _super;

    //inherited
    public final StringPath applyMotive;

    //inherited
    public final BooleanPath attendance;

    //inherited
    public final NumberPath<Integer> grade;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final EnumPath<InflowPath> inflowPath;

    //inherited
    public final BooleanPath isApproved;

    //inherited
    public final StringPath preQuestions;

    // inherited
    public final com.letsintern.letsintern.domain.program.domain.QProgram program;

    //inherited
    public final NumberPath<Long> reviewId;

    //inherited
    public final EnumPath<ApplicationStatus> status;

    public final com.letsintern.letsintern.domain.user.domain.QUser user;

    //inherited
    public final StringPath wishCompany;

    //inherited
    public final StringPath wishJob;

    public QUserApplication(String variable) {
        this(UserApplication.class, forVariable(variable), INITS);
    }

    public QUserApplication(Path<? extends UserApplication> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserApplication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserApplication(PathMetadata metadata, PathInits inits) {
        this(UserApplication.class, metadata, inits);
    }

    public QUserApplication(Class<? extends UserApplication> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QApplication(type, metadata, inits);
        this.applyMotive = _super.applyMotive;
        this.attendance = _super.attendance;
        this.grade = _super.grade;
        this.id = _super.id;
        this.inflowPath = _super.inflowPath;
        this.isApproved = _super.isApproved;
        this.preQuestions = _super.preQuestions;
        this.program = _super.program;
        this.reviewId = _super.reviewId;
        this.status = _super.status;
        this.user = inits.isInitialized("user") ? new com.letsintern.letsintern.domain.user.domain.QUser(forProperty("user")) : null;
        this.wishCompany = _super.wishCompany;
        this.wishJob = _super.wishJob;
    }

}

