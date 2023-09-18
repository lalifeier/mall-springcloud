package com.github.lalifeier.mall.cloud.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntity is a Querydsl query type for BaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntity extends EntityPathBase<BaseEntity<? extends java.io.Serializable>> {

    private static final long serialVersionUID = -2087293120L;

    public static final QBaseEntity baseEntity = new QBaseEntity("baseEntity");

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final SimplePath<java.io.Serializable> id = createSimple("id", java.io.Serializable.class);

    public final NumberPath<Long> updatedBy = createNumber("updatedBy", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedTime = createDateTime("updatedTime", java.time.LocalDateTime.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(String variable) {
        super((Class) BaseEntity.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(Path<? extends BaseEntity> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QBaseEntity(PathMetadata metadata) {
        super((Class) BaseEntity.class, metadata);
    }

}

