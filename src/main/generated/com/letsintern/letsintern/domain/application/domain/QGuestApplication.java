package com.letsintern.letsintern.domain.application.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuestApplication is a Querydsl query type for GuestApplication
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGuestApplication extends EntityPathBase<GuestApplication> {

    private static final long serialVersionUID = -946923033L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuestApplication guestApplication = new QGuestApplication("guestApplication");

    public final QApplication _super;

    //inherited
    public final StringPath applyMotive;

    //inherited
    public final BooleanPath attendance;

    //inherited
    public final NumberPath<Integer> grade;

    public final StringPath guestEmail = createString("guestEmail");

    public final StringPath guestName = createString("guestName");

    public final StringPath guestPhoneNum = createString("guestPhoneNum");

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final BooleanPath isApproved;

    //inherited
    public final StringPath preQuestions;

    // inherited
    public final com.letsintern.letsintern.domain.program.domain.QProgram program;

    //inherited
    public final NumberPath<Long> reviewId;

    //inherited
    public final StringPath wishCompany;

    //inherited
    public final StringPath wishJob;

    public QGuestApplication(String variable) {
        this(GuestApplication.class, forVariable(variable), INITS);
    }

    public QGuestApplication(Path<? extends GuestApplication> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuestApplication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuestApplication(PathMetadata metadata, PathInits inits) {
        this(GuestApplication.class, metadata, inits);
    }

    public QGuestApplication(Class<? extends GuestApplication> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QApplication(type, metadata, inits);
        this.applyMotive = _super.applyMotive;
        this.attendance = _super.attendance;
        this.grade = _super.grade;
        this.id = _super.id;
        this.isApproved = _super.isApproved;
        this.preQuestions = _super.preQuestions;
        this.program = _super.program;
        this.reviewId = _super.reviewId;
        this.wishCompany = _super.wishCompany;
        this.wishJob = _super.wishJob;
    }

}

