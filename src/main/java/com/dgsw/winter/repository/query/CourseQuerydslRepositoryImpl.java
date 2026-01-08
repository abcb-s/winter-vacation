package com.dgsw.winter.repository.query;

import com.dgsw.winter.entity.Course;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.dgsw.winter.entity.QCourse.*;

@Repository
@RequiredArgsConstructor
public class CourseQuerydslRepositoryImpl implements CourseQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

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

    public List<Course> findByNameStartingWith(String name) {
        List<Tuple> fetch = jpaQueryFactory
                .select(course.id, course.name)
                .from(course)
                .where(course.name.startsWith(name))
                .fetch();
        return null;
    }
}
