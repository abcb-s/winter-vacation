package com.dgsw.winter.controller;


import com.dgsw.winter.dto.request.AddArticleRequest;
import com.dgsw.winter.dto.request.UpdateArticleRequest;
import com.dgsw.winter.dto.response.ArticleResponse;
import com.dgsw.winter.entity.Article;
import com.dgsw.winter.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Article> save(@Valid @RequestBody AddArticleRequest request){
        Article article = blogService.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(article);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> findAll(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok(articles);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> findById(
            @PathVariable("id") long id
    ){
        Article article = blogService.findById(id);
        return ResponseEntity.ok(new ArticleResponse(article));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(
            @Valid
            @PathVariable("id") long id,
            @RequestBody UpdateArticleRequest request
    ){
        Article article = blogService.updateArticle(id,request);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") long id
    ){
        blogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
