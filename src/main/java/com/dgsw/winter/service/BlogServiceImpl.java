package com.dgsw.winter.service;

import com.dgsw.winter.dto.request.AddArticleRequest;
import com.dgsw.winter.dto.request.UpdateArticleRequest;
import com.dgsw.winter.entity.Article;
import com.dgsw.winter.exception.ArticleNotFoundException;
import com.dgsw.winter.exception.CustomException;
import com.dgsw.winter.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(
        rollbackFor = Exception.class,
        timeout = 5
)
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    @Override
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    @Override
    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public void delete(Long id){
        Article article
                = blogRepository.findById(id)
                .orElseThrow(
                        ArticleNotFoundException::new
                );
        blogRepository.delete(article);
    }

    @Override
    public Article updateArticle(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(
                        ArticleNotFoundException::new
                );
        article.update(request.getTitle(),request.getContent());
        return article;
    }


}
