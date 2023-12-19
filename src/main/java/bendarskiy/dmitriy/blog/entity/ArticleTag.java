package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class ArticleTag {

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "tag_id")
    private String tagId;
}
