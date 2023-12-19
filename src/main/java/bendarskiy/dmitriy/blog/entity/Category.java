package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Category {
    private Long id;

    @Column(name = "category")
    private String categoryName;
}
