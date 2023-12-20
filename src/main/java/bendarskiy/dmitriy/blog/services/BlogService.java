
package bendarskiy.dmitriy.blog.services;

import bendarskiy.dmitriy.blog.dto.ArticleDTO;
import bendarskiy.dmitriy.blog.dto.CategoryDTO;
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
import org.springframework.transaction.annotation.Transactional;

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

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(article -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setName(article.getName());
            articleDTO.setText(article.getText());
            articleDTO.setCreatedAt(article.getCreatedAt());
            articleDTO.setCategories(articleCategoryRepository.findByArticleId(article.getId()).stream().map(articleCategory -> {
                Category category = categoryRepository.findById(articleCategory.getCategoryId()).orElse(new Category());
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(category.getId());
                categoryDTO.setCategoryName(category.getCategoryName());
                return categoryDTO;
            }).toList());
            articleDTO.setTags(articleTagRepository.findByArticleId(article.getId()).stream().map(ArticleTag::getTag).toList());
            return articleDTO;
        }).toList();
    }

    public List<ArticleDTO> getArticlesByTag(String tag) {
        return articleTagRepository.findByTag(tag)
                .stream()
                .map(articleTag -> getArticleDTO(articleTag.getArticleId()))
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getArticlesByCategory(String categoryId) {
        return articleCategoryRepository.findByCategoryId(categoryId)
                .stream()
                .map(articleCategory -> getArticleDTO(articleCategory.getArticleId()))
                .toList();
    }

    private ArticleDTO getArticleDTO(String articleId) {
        ArticleDTO articleDTO = new ArticleDTO();
        Article article = articleRepository.findById(articleId).orElse(null);
        List<String> tags = articleTagRepository.findByArticleId(article.getId())
                .stream()
                .map(articleTag -> articleTag.getTag())
                .toList();
        List<CategoryDTO> categories = articleCategoryRepository.findByArticleId(article.getId())
                .stream()
                .map(articleCategory1 -> categoryRepository.findById(articleCategory1.getCategoryId()).map(category -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(category.getId());
                    categoryDTO.setCategoryName(category.getCategoryName());
                    return categoryDTO;
                }).orElse(null))
                .toList();

        articleDTO.setId(article.getId());
        articleDTO.setName(article.getName());
        articleDTO.setText(article.getText());
        articleDTO.setCreatedAt(article.getCreatedAt());
        articleDTO.setCategories(categories);
        articleDTO.setTags(tags);
        return articleDTO;
    }

    @Transactional
    public void addArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        String articleId = UUID.randomUUID().toString();
        article.setId(articleId);
        article.setName(articleDTO.getName());
        article.setText(articleDTO.getText());
        article.setCreatedAt(LocalDateTime.now());
        articleRepository.save(article);

        articleDTO.getCategories().forEach(categoryDTO -> {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setId(UUID.randomUUID().toString());
            articleCategory.setArticleId(articleId);
            articleCategory.setCategoryId(categoryDTO.getId());
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

    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(String categoryId) {
        articleCategoryRepository.deleteByCategoryId(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public void deleteTag(String tag) {
        articleTagRepository.deleteByTag(tag);
    }

    @Transactional
    public void deleteArticle(String articleId) {
        articleCategoryRepository.deleteByArticleId(articleId);
        articleTagRepository.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }

}
