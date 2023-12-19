package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Article {

    private Long id;

    private String name;

    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
