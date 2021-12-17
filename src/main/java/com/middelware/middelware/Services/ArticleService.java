package com.middelware.middelware.Services;

import com.middelware.middelware.Models.Article;
import com.middelware.middelware.Models.Theme;
import com.middelware.middelware.Models.User;
import com.middelware.middelware.Repo.ArticleRepo;
import com.middelware.middelware.dto.CreateArticle;
import com.middelware.middelware.dto.UpdateArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Component
@Transactional
public class ArticleService {
    @Autowired
    private  ArticleRepo articleRepo;
    @Autowired
    private  UserService userService;
    @Autowired
    private ThemeService themeService;

    public Article createArticle(CreateArticle createArticle){
        User coach=userService.getUserById(createArticle.getCoachId());
        Theme theme=themeService.getThemeById(createArticle.getThemeId());
        Date date=new Date();
        Article article=new Article(null,createArticle.getTitle(),createArticle.getDescription(),createArticle.getImage(),false,date,theme,coach);
        return articleRepo.save(article);
    }
    public List<Article> allArticles(){ return articleRepo.findAll();}
    public Article getArticleById(Long id){return articleRepo.findById(id).get();}
    public Article validateArticle(Long id){
        Article article=getArticleById(id);
        article.setIsValidated(true);
        return articleRepo.save(article);
    }
    public Article updateArticleCoach(UpdateArticle updateArticle){
        Article article=getArticleById(updateArticle.getId());
        article.setTitle(updateArticle.getTitle());
        article.setImage(updateArticle.getImage());
        article.setDescription(updateArticle.getDescription());
        return articleRepo.save(article);
    }


    public Article updateArticle(Article article){return  articleRepo.save(article);}
    public void deleteArticle(Long id){
        Article article=getArticleById(id);
        article.setIsValidated(false);
        articleRepo.save(article);}
    public List<Article> getArticlesByTitle(String title){
        return articleRepo.getArticlesByTitleAndIsValidatedTrue(title);
    }
    public List<Article> getArticlesByTheme(Long idTheme){
        return articleRepo.getArticlesByThemeIdAndIsValidatedTrue(idTheme);
    }

    public List<Article> getArticlesByCoach(Long idCoach){
        return articleRepo.getArticlesByCoachIdAndIsValidated(idCoach,true);
    }
    public List<Article> getValidatedArticles(){
        return articleRepo.getArticlesByIsValidated(true);
    }
    public List<Article> getNotValidatedArticles(){
        return articleRepo.getArticlesByIsValidated(false);
    }
    public List<Article> getNotValidatedArticlesByCoach(Long idCoach){
        return articleRepo.getArticlesByCoachIdAndIsValidated(idCoach, false);
    }
}