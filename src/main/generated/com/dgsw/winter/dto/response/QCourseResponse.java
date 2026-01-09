package com.dgsw.winter.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.annotations.Generated;

/**
 * com.dgsw.winter.dto.response.QCourseResponse is a Querydsl Projection type for CourseResponse
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCourseResponse extends ConstructorExpression<CourseResponse> {

    private static final long serialVersionUID = -673648149L;

    public QCourseResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<Integer> rating, com.querydsl.core.types.Expression<String> description) {
        super(CourseResponse.class, new Class<?>[]{long.class, String.class, String.class, int.class, String.class}, id, name, category, rating, description);
    }

}

