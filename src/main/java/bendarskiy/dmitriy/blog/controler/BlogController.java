package bendarskiy.dmitriy.blog.controler;

import bendarskiy.dmitriy.blog.dto.ArticleDTO;
import bendarskiy.dmitriy.blog.dto.CategoryDTO;
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

    @GetMapping(value = "/article")
    public ResponseEntity<List<ArticleDTO>> getArticles() {
        final List<ArticleDTO> articles = blogService.getAllArticles();

        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getCategories() {
        final List<Category> categories = blogService.getAllCategories();

        return categories != null && !categories.isEmpty()
                ? new ResponseEntity<>(categories, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/article/category/{categoryId}")
    public ResponseEntity<List<ArticleDTO>> getArticlesByCategoryId(@PathVariable(name = "categoryId") String categoryId) {
        final List<ArticleDTO> articles = blogService.getArticlesByCategory(categoryId);

        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/article/tag/{tag}")
    public ResponseEntity<List<ArticleDTO>> getArticlesByTag(@PathVariable(name = "tag") String tag) {
        final List<ArticleDTO> articles = blogService.getArticlesByTag(tag);

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
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        blogService.addCategory(categoryDTO);
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

    @DeleteMapping(value = "/tag/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") String id) {
        blogService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
