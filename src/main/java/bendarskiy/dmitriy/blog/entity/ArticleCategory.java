package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class ArticleCategory {

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "category_id")
    private String categoryId;
}
