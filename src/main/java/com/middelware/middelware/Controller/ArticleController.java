package com.middelware.middelware.Controller;

import com.middelware.middelware.Models.Article;
import com.middelware.middelware.Services.ArticleService;
import com.middelware.middelware.dto.CreateArticle;
import com.middelware.middelware.dto.UpdateArticle;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/Article")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ArticleController {
    @Autowired
    private ArticleService articleService;

   // @PreAuthorize("hasAnyRole('ADMIN','COACH')")
    @PostMapping("")//valid
    public Article createArticle(@RequestBody CreateArticle createArticle) {
        return articleService.createArticle(createArticle);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")//valid
    public List<Article> getAllArticles() {
        return articleService.allArticles();
    }

    @GetMapping("/{id}")//valid
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/title/{title}")//valid
    public List<Article> getArticleByTitle(@PathVariable String title) {
        return articleService.getArticlesByTitle(title);
    }
    @GetMapping("/theme/{theme_id}")//valid
    public List<Article> getArticlesByTheme(@PathVariable Long theme_id) {
        return articleService.getArticlesByTheme(theme_id);
    }

    @GetMapping("/coachId/{coach_id}")//valid
    public List<Article> getArticlesByCoach(@PathVariable Long coach_id) {
        return articleService.getArticlesByCoach(coach_id);
    }
    @GetMapping("/validatedArticles")//valid
    public List<Article> getValidatedArticles() {
        return articleService.getValidatedArticles();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notValidatedArticles")//valid
    public List<Article> getNotValidatedArticles() {
        return articleService.getNotValidatedArticles();
    }

    //@PreAuthorize("hasRole('COACH')")
    @GetMapping("/notValidatedArticlesByCoach/{id_coach}")//valid
    public List<Article> getNotValidatedArticlesByCoach(@PathVariable Long id_coach) {
        return articleService.getNotValidatedArticlesByCoach(id_coach);
    }

    @PutMapping("")//valid
    public Article updateArticleCoach(@RequestBody UpdateArticle updateArticle) {
        return articleService.updateArticleCoach(updateArticle);
    }

    @PutMapping("/admin")//valid
    public Article updateArticleAdmin(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @PutMapping("/validate/{articleId}")//valid
    public Article validateArticle(@PathVariable Long articleId) {
        return articleService.validateArticle(articleId);
    }

    @DeleteMapping("/{id}")//valid
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
