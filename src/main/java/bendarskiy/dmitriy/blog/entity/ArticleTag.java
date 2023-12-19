package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class ArticleTag {

    @Id
    private String id;

    @Column(name = "article_id")
    private String articleId;

    private String tag;
}
