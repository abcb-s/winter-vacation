package com.dgsw.winter.repository;

import com.dgsw.winter.entity.Course;
import org.apache.logging.log4j.util.PropertySource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourseQueryMethodRepositoryTest {
    @Autowired
    private CourseQueryMethodRepository courseQueryMethodRepository;

    @Test
    void findByName(){
        Optional<Course> existingCourse = courseQueryMethodRepository.findByName("Java Fundamentals");
        Optional<Course> nonExistingCourse = courseQueryMethodRepository.findByName("kotlin basics");

        assertThat(existingCourse)
                .isPresent()
                .get()
                .extracting(Course::getCategory)
                .isEqualTo("Java");
//        assertThat(existingCourse.get().getCategory()).isEqualTo("Java");
        assertThat(nonExistingCourse).isNotPresent();
    }

    @Test
    void findAllByCategory(){
        List<Course> result = courseQueryMethodRepository.findAllByCategory("Spring");

        assertThat(result)
                .hasSize(3)
                .allMatch(course -> "Spring".equals(course.getCategory()));
    }

    @Test
    void findAllByCategoryOrderByName(){
        List<Course> result = courseQueryMethodRepository.findAllByCategoryOrderByName("Spring");
        assertThat(result)
                .hasSize(3)
                .extracting(Course::getName)
                .isSortedAccordingTo(Comparator.reverseOrder());

    }

    @Test
    void countByCategory(){
        long result = courseQueryMethodRepository.countByCategory("Spring");

        assertThat(result)
                .isEqualTo(3);
    }

}