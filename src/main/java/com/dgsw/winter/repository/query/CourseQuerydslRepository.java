package com.dgsw.winter.repository.query;


import com.dgsw.winter.dto.response.CourseResponse;
import com.dgsw.winter.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseQuerydslRepository {
    Optional<Course> findByName(String name);

    List<Course> findByCategoryAndMinRating(
            String category, int minRating
    );

    List<CourseResponse> findByNameStartingWith(String name);

    List<CourseResponse> findAll();

    List<CourseResponse> findAll2();

    List<CourseResponse> findByConditions(String category, Integer minRating, String namePrefix);
}
