package bendarskiy.dmitriy.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Tag {
    private Long id;

    @Column(name = "tag")
    private String tagName;
}
