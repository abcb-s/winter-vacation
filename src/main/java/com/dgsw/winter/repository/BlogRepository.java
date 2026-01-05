package com.dgsw.winter.repository;

import com.dgsw.winter.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {

    Optional<Article> findOneByTitle(String title);//query method
}
