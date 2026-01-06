package com.dgsw.winter.service;

import com.dgsw.winter.dto.request.AddArticleRequest;
import com.dgsw.winter.dto.request.UpdateArticleRequest;
import com.dgsw.winter.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    Article save(AddArticleRequest request);

    List<Article> findAll();

    Article findById(Long id);

    void delete(Long id);

    Article updateArticle(long id, UpdateArticleRequest request);
}
