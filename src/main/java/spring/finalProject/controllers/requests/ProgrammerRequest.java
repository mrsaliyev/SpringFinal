package spring.finalProject.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammerRequest {
    private String name, lastName, email, img, description;
}
