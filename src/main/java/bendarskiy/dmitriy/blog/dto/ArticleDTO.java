package bendarskiy.dmitriy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

    private String name;

    private String text;

    private List<String> categories;

    private List<String> tags;
}
