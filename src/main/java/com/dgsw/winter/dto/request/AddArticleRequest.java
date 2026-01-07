package com.dgsw.winter.dto.request;

import com.dgsw.winter.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    @NotBlank(message = "게시글 제목을 입력해주세요")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해주세요")
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}

