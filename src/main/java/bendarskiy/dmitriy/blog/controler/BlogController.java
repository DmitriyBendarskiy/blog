package bendarskiy.dmitriy.blog.controler;

import bendarskiy.dmitriy.blog.dto.ArticleDTO;
import bendarskiy.dmitriy.blog.entity.Article;
import bendarskiy.dmitriy.blog.entity.Category;
import bendarskiy.dmitriy.blog.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping(value = "/articles")
    public ResponseEntity<List<Article>> getArticles() {
        final List<Article> articles = blogService.getAllArticles();

        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getCategories() {
        final List<Category> categories = blogService.getAllCategories();

        return categories != null && !categories.isEmpty()
                ? new ResponseEntity<>(categories, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/articles/category/{categoryId}")
    public ResponseEntity<List<Article>> getArticlesByCategoryId(@PathVariable(name = "categoryId") String categoryId) {
        final List<Article> articles = blogService.getArticlesByCategory(categoryId);

        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/articles/blog/{categoryId}")
    public ResponseEntity<List<Article>> getArticlesBTag(@PathVariable(name = "categoryId") String tag) {
        final List<Article> articles = blogService.getArticlesByTag(tag);

        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/article")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO articleDTO) {
        blogService.addArticle(articleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/category")
    public ResponseEntity<?> createArticle(@RequestBody String categoryName) {
        blogService.addCategory(categoryName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable(name = "id") String id) {
        blogService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") String id) {
        blogService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
