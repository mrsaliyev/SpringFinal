package spring.finalProject.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    private String title, img, shortDescription, body;
    private Long categoryId;
}
