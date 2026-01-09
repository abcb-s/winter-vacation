package com.dgsw.winter.repository;

import com.dgsw.winter.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseQueryRepository extends JpaRepository<Course, Long> {


    @Query("SELECT c FROM Course c WHERE c.name = :name OR c.category = :category")
    List<Course> findByNameOrCategory(@Param("name") String name,@Param("category") String category);

    @Query("SELECT c FROM Course c WHERE c.category = :category AND c.rating >= : minRating")
    List<Course> findByCategoryAndMinRating(@Param("category") String category, @Param("minRating") int MinRating);

    @Query(value = "SELECT * FROM tb_courses WHERE category = :category",nativeQuery = true)
    List<Course> findByCategoryNative(@Param("category") String category);

    List<Course> findByNameStartingWith(String name);
}
