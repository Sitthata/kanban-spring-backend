package sit.kmutt.kanbanspringbackend.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sit.kmutt.kanbanspringbackend.model.authentication.LoginRequest;
import sit.kmutt.kanbanspringbackend.model.authentication.User;
import sit.kmutt.kanbanspringbackend.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final Argon2 argon;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
    }

    @GetMapping("/users")
    public List<User> all() {
        return userService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user != null && argon.verify(user.getPassword(), loginRequest.getPassword().toCharArray())) {
            request.setAttribute("currentUser", user);
            return ResponseEntity.ok("User Authenticated successfully");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            if (userService.findByUsername(user.getUsername()) != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Error: Username is already taken!");
            }

            if (userService.findByEmail(user.getEmail()) != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Error: Email is already in use!");
            }
            User newUser = userService.createUser(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(String.format("User create successfully with id: %d, username: %s, gmail: %s"
                            , newUser.getId(), newUser.getUsername(), newUser.getEmail()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing your request");
        }
    }
}
