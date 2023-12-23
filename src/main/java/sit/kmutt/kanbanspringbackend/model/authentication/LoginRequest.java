package sit.kmutt.kanbanspringbackend.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
