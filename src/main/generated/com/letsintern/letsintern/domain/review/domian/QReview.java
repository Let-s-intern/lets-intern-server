package com.letsintern.letsintern.domain.review.domian;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -561366279L;

    public static final QReview review = new QReview("review");

    public final StringPath createdAt = createString("createdAt");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> programId = createNumber("programId", Long.class);

    public final StringPath reviewContents = createString("reviewContents");

    public final EnumPath<ReviewStatus> status = createEnum("status", ReviewStatus.class);

    public final StringPath suggestContents = createString("suggestContents");

    public final StringPath userName = createString("userName");

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

