package com.dgsw.winter.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public Article(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

