package com.dgsw.winter.controller;

import com.dgsw.winter.base.BaseController;
import com.dgsw.winter.dto.request.AddArticleRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlogControllerTest extends BaseController {


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
}
