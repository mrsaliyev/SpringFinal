package spring.finalProject.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestNewPost {
    private String title;
    private String body;
    private String footer;
    private String imgPath;
    private String shortDesc;
}
