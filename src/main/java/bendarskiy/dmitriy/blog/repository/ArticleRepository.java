package bendarskiy.dmitriy.blog.repository;

import bendarskiy.dmitriy.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
}
