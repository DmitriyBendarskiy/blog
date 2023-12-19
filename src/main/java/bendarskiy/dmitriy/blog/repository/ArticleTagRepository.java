

package bendarskiy.dmitriy.blog.repository;

import bendarskiy.dmitriy.blog.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, String> {
    void deleteByArticleId(String articleId);
    List<ArticleTag> findByTag(String tag);
}
