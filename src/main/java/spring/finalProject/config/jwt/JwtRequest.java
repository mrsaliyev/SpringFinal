package spring.finalProject.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String username;
    private String password;
}
