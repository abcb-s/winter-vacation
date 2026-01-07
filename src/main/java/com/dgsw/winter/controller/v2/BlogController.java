package com.dgsw.winter.controller.v2;

import com.dgsw.winter.dto.request.ArticleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("blogControllerV2")
@RequestMapping("/api/v2/articles")
public class BlogController {

    @PostMapping
    public ResponseEntity save(@Validated(ArticleRequest.SaveGroup.class) @RequestBody ArticleRequest request) {
        System.out.println("========== save() ");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @Validated(ArticleRequest.UpdateGroup.class) @RequestBody ArticleRequest request
    ){
        System.out.println("========== update() ");
        return ResponseEntity.ok().build();
    }
}
