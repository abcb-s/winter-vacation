package com.dgsw.winter.repository.query;

import com.dgsw.winter.dto.response.CourseResponse;
import com.dgsw.winter.dto.response.QCourseResponse;
import com.dgsw.winter.entity.Course;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

import static com.dgsw.winter.entity.QCourse.*;

@Repository
@RequiredArgsConstructor
public class CourseQuerydslRepositoryImpl implements CourseQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final RestClient.Builder builder;

    @Override
    public Optional<Course> findByName(String name) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(course)
                .where(course.name.eq(name))
                .fetchOne()
        );
    }

    @Override
    public List<Course> findByCategoryAndMinRating(
            String category, int minRating
    ){
        return jpaQueryFactory
                .selectFrom(course)
                .where(course.category.eq(category),
                        course.rating.goe(minRating))
                .orderBy(course.id.desc())
                .fetch();
    }
    @Override
    public List<CourseResponse> findByNameStartingWith(String name) {
        return jpaQueryFactory
                .select(course.id, course.name)
                .from(course)
                .where(course.name.startsWith(name))
                .fetch()
                .stream()
                .map(tuple -> new CourseResponse(
                        tuple.get(course.id),
                        tuple.get(course.name)
                        )
                )
                .toList();
    }

     @Override
    public List<CourseResponse> findAll(){
        return jpaQueryFactory
                .select(courseResponseProjection())
                .from(course)
                .orderBy(course.id.desc())
                .fetch();
    }

    private ConstructorExpression<CourseResponse> courseResponseProjection() {
        return Projections.constructor(
                CourseResponse.class,
                course.id,
                course.name,
                course.category,
                course.rating
        );
    }

    @Override
    public List<CourseResponse> findAll2(){
        return jpaQueryFactory.select(
                courseResponseProjection2()
        ).from(course)
                .orderBy(course.id.desc())
                .fetch();
    }

    private  ConstructorExpression<CourseResponse> courseResponseProjection2() {
        return new QCourseResponse(
                course.id,
                course.name,
                course.category,
                course.rating,
                course.description
        );
    }

    @Override
    public List<CourseResponse> findByConditions(String category, Integer minRating, String namePrefix){
        jpaQueryFactory
                .select(courseResponseProjection())
                .from(course)
                .where(
                        eqCategory(category),
                        goeMinRating(minRating),
                        startsWithName(namePrefix)
                ).fetch();
        return null;
    }

    private BooleanExpression startsWithName(String namePrefix) {
        return StringUtils.hasText(namePrefix)
                ? course.name.startsWith(namePrefix)
                : null;
    }

    private BooleanExpression goeMinRating(Integer minRating) {
        return minRating != null
                ? course.rating.goe(minRating)
                : null;
    }

    private BooleanExpression eqCategory(String category){
        return StringUtils.hasText(category)
                ? course.category.eq(category)
                : null;
    }

    public List<CourseResponse> findByConditionsWithBuilder(String type, String keyword){
        return jpaQueryFactory
                .select(courseResponseProjection())
                .from(course)
                .where(
                        buildKeywordCondition(type,keyword)
                ).fetch();
    }

    private BooleanExpression buildKeywordCondition(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        String upperType = type.toUpperCase();

        if (upperType.contains("N")) {
            builder.or(course.name.contains(keyword));
        }
        if (upperType.contains("C")) {
            builder.or(course.category.contains(keyword));
        }
        if (upperType.contains("D")) {
            builder.or(course.description.contains(keyword));
        }

        return builder.hasValue() ? builder : null;
    }

}
