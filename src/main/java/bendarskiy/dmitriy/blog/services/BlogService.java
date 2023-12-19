
package bendarskiy.dmitriy.blog.services;

import bendarskiy.dmitriy.blog.dto.ArticleDTO;
import bendarskiy.dmitriy.blog.entity.Article;
import bendarskiy.dmitriy.blog.entity.ArticleCategory;
import bendarskiy.dmitriy.blog.entity.ArticleTag;
import bendarskiy.dmitriy.blog.entity.Category;
import bendarskiy.dmitriy.blog.repository.ArticleCategoryRepository;
import bendarskiy.dmitriy.blog.repository.ArticleRepository;
import bendarskiy.dmitriy.blog.repository.ArticleTagRepository;
import bendarskiy.dmitriy.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BlogService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Autowired
    private ArticleTagRepository articleTagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<Article> getArticlesByTag(String tag) {
        return articleTagRepository.findByTag(tag)
                .stream()
                .map(articleTag -> articleRepository.findById(articleTag.getArticleId()).orElse(null))
                .collect(Collectors.toList());
    }

    public List<Article> getArticlesByCategory(String categoryId) {
        return articleCategoryRepository.findByCategoryId(categoryId)
                .stream()
                .map(articleCategory -> articleRepository.findById(articleCategory.getArticleId()).orElse(null))
                .collect(Collectors.toList());
    }

    public void addArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        String articleId = UUID.randomUUID().toString();
        article.setId(articleId);
        article.setName(articleDTO.getName());
        article.setText(articleDTO.getText());
        article.setCreatedAt(LocalDateTime.now());
        articleRepository.save(article);

        articleDTO.getCategories().forEach(categoryId -> {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setId(UUID.randomUUID().toString());
            articleCategory.setArticleId(articleId);
            articleCategory.setCategoryId(categoryId);
            articleCategoryRepository.save(articleCategory);
        });

        articleDTO.getTags().forEach(tag -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setId(UUID.randomUUID().toString());
            articleTag.setArticleId(articleId);
            articleTag.setTag(tag);
            articleTagRepository.save(articleTag);
        });
    }

    public void addCategory(String categoryName) {
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
    }

    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
        articleCategoryRepository.deleteByCategoryId(categoryId);
    }

    public void deleteArticle(String articleId) {
        articleRepository.deleteById(articleId);
        articleCategoryRepository.deleteByArticleId(articleId);
        articleTagRepository.deleteByArticleId(articleId);
    }

}
