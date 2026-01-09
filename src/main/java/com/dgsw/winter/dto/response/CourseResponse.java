package com.dgsw.winter.dto.response;

import com.dgsw.winter.entity.Course;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CourseResponse {

    private Long id;

    private String name;

    private String category;

    private int rating;

    private String description;


    @QueryProjection
    public CourseResponse(Long id, String name, String category, int rating, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }

    public CourseResponse(Long id, String name,String category,int rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
    }

    public CourseResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
