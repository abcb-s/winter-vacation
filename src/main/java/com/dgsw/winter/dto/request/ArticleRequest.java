package com.dgsw.winter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleRequest {

    public interface SaveGroup {
    }
    public interface UpdateGroup {
    }
    interface DeleteGroup {}

    @NotBlank(groups = {SaveGroup.class, UpdateGroup.class}, message = "게시글 제목을 입력해주세요.")
    private String title;

    @NotBlank(groups = {SaveGroup.class}, message = "게시글 내용을 입력해주세요")
    private String content;
}
