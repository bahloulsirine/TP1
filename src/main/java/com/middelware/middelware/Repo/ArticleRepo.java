package com.middelware.middelware.Repo;

import com.middelware.middelware.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {
    List<Article> getArticlesByTitleAndIsValidatedTrue(String title);
    List<Article>getArticlesByThemeIdAndIsValidatedTrue(Long themeId);

    List<Article>getArticlesByIsValidated(Boolean test);
    List<Article>getArticlesByCoachIdAndIsValidated(Long coachId,Boolean test);
}
