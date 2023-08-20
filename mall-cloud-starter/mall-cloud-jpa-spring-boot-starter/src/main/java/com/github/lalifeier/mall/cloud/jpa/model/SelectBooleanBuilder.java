package com.github.lalifeier.mall.cloud.jpa.model;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.Consumer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/** 构建 BooleanBuilder. */
public class SelectBooleanBuilder extends SelectBuilder<BooleanBuilder> {

    private final BooleanBuilder builder;

    public SelectBooleanBuilder() {
        this.builder = new BooleanBuilder();
    }

    public SelectBooleanBuilder(BooleanBuilder builder) {
        this.builder = builder == null ? new BooleanBuilder() : builder;
    }

    @Override
    public BooleanBuilder getPredicate() {
        return builder;
    }

    public SelectBooleanBuilder and(@Nullable Predicate right) {
        builder.and(right);
        return this;
    }

    public SelectBooleanBuilder andAnyOf(Predicate... args) {
        builder.andAnyOf(args);
        return this;
    }

    public SelectBooleanBuilder andNot(Predicate right) {
        return and(right.not());
    }

    public SelectBooleanBuilder or(@Nullable Predicate right) {
        builder.or(right);
        return this;
    }

    public SelectBooleanBuilder orAllOf(Predicate... args) {
        builder.orAllOf(args);
        return this;
    }

    public SelectBooleanBuilder orNot(Predicate right) {
        return or(right.not());
    }

    public SelectBooleanBuilder notNullEq(Boolean param, BooleanPath path) {
        if (param != null) {
            builder.and(path.eq(param));
        }
        return this;
    }

    public <T extends Number & Comparable<?>> SelectBooleanBuilder notNullEq(
            T param, NumberPath<T> path) {
        if (param != null) {
            builder.and(path.eq(param));
        }
        return this;
    }

    public SelectBooleanBuilder isIdEq(Long param, NumberPath<Long> path) {
        if (param != null && param > 0L) {
            builder.and(path.eq(param));
        }
        return this;
    }

    public <T extends Number & Comparable<?>> SelectBooleanBuilder leZeroIsNull(
            T param, NumberPath<T> path) {
        if (param != null && param.longValue() <= 0) {
            builder.and(path.isNull());
        }
        return this;
    }

    public SelectBooleanBuilder notBlankEq(String param, StringPath path) {
        if (StringUtils.isNoneBlank(param)) {
            builder.and(path.eq(param));
        }
        return this;
    }

    public SelectBooleanBuilder with(@NotNull Consumer<SelectBooleanBuilder> consumer) {
        if (consumer != null) {
            consumer.accept(this);
        }
        return this;
    }

    public <T extends Enum<T>> SelectBooleanBuilder notNullEq(T param, EnumPath<T> path) {
        if (param != null) {
            builder.and(path.eq(param));
        }
        return this;
    }

    public SelectBooleanBuilder notBlankContains(String param, StringPath path) {
        if (StringUtils.isNoneBlank(param)) {
            builder.and(path.contains(param));
        }
        return this;
    }

    // public SelectBooleanBuilder notNullEq(Dict dict, QDict qDict) {
    //  if (dict != null && StringUtils.isNoneBlank(dict.getKey())) {
    //    builder.and(qDict.eq(dict));
    //  }
    //  return this;
    // }

    public SelectBooleanBuilder notNullBefore(
            LocalDateTime param, DateTimePath<LocalDateTime> path) {
        if (param != null) {
            builder.and(path.before(param));
        }
        return this;
    }

    public SelectBooleanBuilder notNullAfter(
            LocalDateTime param, DateTimePath<LocalDateTime> path) {
        if (param != null) {
            builder.and(path.after(param));
        }
        return this;
    }

    public SelectBooleanBuilder notEmptyIn(
            Collection<? extends Long> param, NumberPath<Long> path) {
        if (CollectionUtils.isNotEmpty(param)) {
            builder.and(path.in(param));
        }
        return this;
    }

    public SelectBooleanBuilder findInSet(Long param, SetPath<Long, NumberPath<Long>> path) {
        if (param != null) {
            builder.and(Expressions.booleanTemplate("FIND_IN_SET({0}, {1}) > 0", param, path));
        }
        return this;
    }
}
