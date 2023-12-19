package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Article {

    @Id
    private String id;

    private String name;

    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
