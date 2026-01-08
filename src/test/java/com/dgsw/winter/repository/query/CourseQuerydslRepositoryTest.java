package com.dgsw.winter.repository.query;

import com.dgsw.winter.entity.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseQuerydslRepositoryTest {

    @Autowired
    private CourseQuerydslRepository courseQuerydslRepository;

    @Test
    void findByName(){
        Course course = courseQuerydslRepository.findByName("Spring with Java")
                .orElse(null);

        assertThat(course.getName())
                .isEqualTo("Spring with Java");
    }

    @Test
    void findByCategoryAndMinRating(){
        List<Course> courses
                = courseQuerydslRepository.findByCategoryAndMinRating("Spring with Java", 1);
        assertThat(courses)
                .hasSize(2)
                .allSatisfy(course ->{
                    assertThat(course.getCategory()).isEqualTo("Spring");
                    assertThat(course.getRating()).isGreaterThanOrEqualTo(4);
                });
    }


}