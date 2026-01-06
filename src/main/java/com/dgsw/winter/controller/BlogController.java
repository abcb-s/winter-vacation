package com.dgsw.winter.controller;


import com.dgsw.winter.dto.request.AddArticleRequest;
import com.dgsw.winter.entity.Article;
import com.dgsw.winter.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Article> save(@RequestBody AddArticleRequest request){
        Article article = blogService.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(article);
    }
}
