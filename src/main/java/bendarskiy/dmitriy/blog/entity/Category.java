package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Category {
    @Id
    private String id;

    @Column(name = "category")
    private String categoryName;
}
