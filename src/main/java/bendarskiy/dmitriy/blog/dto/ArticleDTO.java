package bendarskiy.dmitriy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private String id;

    private String name;

    private String text;

    private List<CategoryDTO> categories;

    private List<String> tags;

    private LocalDateTime createdAt;
}
