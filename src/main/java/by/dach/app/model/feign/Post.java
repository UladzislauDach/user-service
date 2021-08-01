package by.dach.app.model.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post {
    private long userId;
    private  long id;
    private String title;
    private String body;
}
