package com.dgsw.winter.repository.query;


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
}
