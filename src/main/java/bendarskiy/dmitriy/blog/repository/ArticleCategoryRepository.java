
package bendarskiy.dmitriy.blog.repository;

import bendarskiy.dmitriy.blog.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, String> {
    void deleteByCategoryId(String categoryId);

    void deleteByArticleId(String articleId);

    List<ArticleCategory> findByCategoryId(String categoryId);
}
