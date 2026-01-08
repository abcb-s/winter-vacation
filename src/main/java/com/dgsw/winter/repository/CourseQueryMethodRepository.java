package com.dgsw.winter.repository;

import com.dgsw.winter.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseQueryMethodRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.category = :category ORDER BY c.name ASC")
    List<Course> findAllByCategoryOrderByName(@Param("category") String category);

    Optional<Course> findByName(String name);

    List<Course> findAllByCategory(String category);

    long countByCategory(String category);
}
