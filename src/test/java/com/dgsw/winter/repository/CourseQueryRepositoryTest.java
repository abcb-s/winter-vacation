package com.dgsw.winter.repository;

import com.dgsw.winter.entity.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseQueryRepositoryTest {

    @Autowired
    private CourseQueryRepository courseQueryRepository;


    @Test
    void findByNameOrCategory(){
        List<Course> result
                = courseQueryRepository.findByNameOrCategory("Java Fundamentals", "Python");

        assertThat(result)
                .hasSize(3)
                .extracting(Course::getCategory)
                .contains("Java", "Python");
    }

    @Test
    void findByCategoryAndMinRating(){
        List<Course> result
                = courseQueryRepository.findByCategoryAndMinRating("Spring",4);

        assertThat(result)
                .isNotEmpty()
                .allSatisfy(course -> {
                    assertThat(course.getCategory())
                            .isEqualTo("Spring");
                    assertThat(course.getRating())
                            .isGreaterThanOrEqualTo(4);
                });
    }

    @Test
    void findByCategoryNative(){
        List<Course> result
                = courseQueryRepository.findByCategoryNative("JavaScript");

        assertThat(result)
                .hasSize(2)
                .allMatch(course -> "JavaScript".equals(course.getCategory()));
    }

}