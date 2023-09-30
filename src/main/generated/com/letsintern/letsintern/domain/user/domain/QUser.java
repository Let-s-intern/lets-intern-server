package com.letsintern.letsintern.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 2033070575L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inflow = createString("inflow");

    public final StringPath joinedAt = createString("joinedAt");

    public final StringPath major = createString("major");

    public final StringPath name = createString("name");

    public final StringPath phoneNum = createString("phoneNum");

    public final NumberPath<Integer> prepareForEmployment = createNumber("prepareForEmployment", Integer.class);

    public final StringPath university = createString("university");

    public final StringPath wishCompany = createString("wishCompany");

    public final StringPath wishJob = createString("wishJob");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

