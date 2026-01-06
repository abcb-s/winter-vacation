package com.dgsw.winter.repository;

import com.dgsw.winter.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @DisplayName("게시글 단일 조회")
    @Test
    void findById() {

        final Long id = 1L;

        Article article = blogRepository.findById(id).orElse(null);

        assertThat(article)
                .isNotNull()
                .extracting(Article::getId)
                .isEqualTo(1L);
    }

    @DisplayName("게시글 전체 조회")
    @Test
    void findAll() {
        List<Article> articles = blogRepository.findAll();
        assertThat(articles)
                .hasSize(5);
    }

    @DisplayName("게시글 등록")
    @Test
    void save() {
        final String title = "테스트 제목";
        final String content = "테스트 내용";

        Article article = new Article(title, content);
        Article saved = blogRepository.save(article);

        assertThat(saved.getTitle()).isEqualTo(title);
        assertThat(saved.getContent()).isEqualTo(content);
    }

    @DisplayName("1 + 2 = 3")
    @Test
    void test() {
        int a = 1;
        int b = 2;
        int sum = 3;
        assertThat(a+b)
                .isEqualTo(sum);
    }

    @DisplayName("문자열 비교 검증")
    @Test
    public void test2() {

        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길순";

        assertThat(name1).isEqualTo("홍길동");
        assertThat(name2).isEqualTo("홍길동");
        assertThat(name3).isNotEqualTo("홍길동");

    }


    @Test
    public void listTest() {

        List<String> users = List.of("홍길동", "임꺽정", "성춘향");

        assertThat(users).hasSize(3);
        assertThat(users).contains("홍길동");
        assertThat(users).doesNotContain("임꺽정");

    }
}