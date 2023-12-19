package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class ArticleCategory {

    @Id
    private String id;

    @Column(name = "article_id")
    private String articleId;

    @Column(name = "category_id")
    private String categoryId;
}
