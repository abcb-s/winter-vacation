package com.dgsw.winter.controller;

import com.dgsw.winter.base.BaseController;
import com.dgsw.winter.dto.request.AddArticleRequest;
import com.dgsw.winter.dto.request.UpdateArticleRequest;
import com.dgsw.winter.dto.response.ArticleResponse;
import com.dgsw.winter.entity.Article;
import com.dgsw.winter.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogControllerTest extends BaseController {

    @Autowired
    private BlogRepository blogRepository;

    @DisplayName("게시글 등록 테스트")
    @Test
    void addArticle() throws Exception {
        final String url = "/api/articles";
        final String title = "새로운 제목";
        final String content = "새로운 내용";
        AddArticleRequest addArticleRequest
                = new AddArticleRequest(title, content);

        mockMvc.perform(
                        post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addArticleRequest))
        ).andExpect(
                jsonPath("$.title").value(title)
        ).andExpect(
                jsonPath("$.content").value(content)
        );

    }

    @DisplayName("게시글 단일 조회 테스트")
    @Test
    void findById() throws Exception {
        final String url = "/api/articles/{id}";
        final long id = 5;

        Optional<Article> article = blogRepository.findById(id);
        assertThat(article).isNotEmpty();
        article.get();

        mockMvc.perform(
                get(url, id)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
          jsonPath("$.title").value(article.get().getTitle())
        ).andExpect(
                jsonPath("$.content").value(article.get().getContent())
        );
    }

    @DisplayName("게시글 단일 조회 실패 테스트")
    @Test
    void findByUd_NotFound() throws Exception {
        final String url = "/api/articles/{id}";
        final long id = 999;

        mockMvc.perform(
                get(url,id).accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isNotFound()
        ).andExpect(
                jsonPath("$.status").value(404)
        );
    }

    @DisplayName("게시글 전체 조회 테스트")
    @Test
    void findAll() throws Exception {
        final String url = "/api/articles";

        MvcResult mvcResult
                = mockMvc.perform(
                get(url).accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        List<ArticleResponse> articleResponses
                = objectMapper.readValue(contentAsString, new TypeReference<List<ArticleResponse>>() {

        });

        Assertions
                .assertThat(articleResponses)
                .hasSize(5)
                .first()
                .satisfies(articleResponse -> {
                    assertThat(articleResponse.getTitle()).isEqualTo("첫 번째 게시글");
                    assertThat(articleResponse.getContent()).isEqualTo("블로그 서비스 개발을 위한 첫 번째 테스트 글입니다.");
                });
//                .andExpect(
//                jsonPath("$[1].title").value("첫 번째 게시글")
//        );
    }

        @DisplayName("게시글 수정 테스트")
        @Test
        void update() throws Exception {
            final String url = "/api/articles/{id}";
            final long id = 5;

            Article addArticleRequest
                    = new Article("새로운 제목","새로운 내용");
            blogRepository.save(addArticleRequest);

            UpdateArticleRequest updateArticleRequest
                    = new UpdateArticleRequest("수정된 제목","수정된 내용");

            mockMvc.perform(
                    put(url, addArticleRequest.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(updateArticleRequest))
            ).andExpect(
                    status().isOk()
            ).andExpect(
                    jsonPath("$.title").value("수정된 제목")
            ).andExpect(
                    jsonPath("$.content").value("수정된 내용")
            );


    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    void delete() throws Exception {
        final String url = "/api/articles/{id}";
        final long id = 18;
        mockMvc.perform(
                MockMvcRequestBuilders.delete(url,id)
        ).andExpect(
                status().isNoContent()
        ).andExpect(
                jsonPath("$.status").value(204)
        );
    }
}
