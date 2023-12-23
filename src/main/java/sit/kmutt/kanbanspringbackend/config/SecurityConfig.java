package sit.kmutt.kanbanspringbackend.config;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sit.kmutt.kanbanspringbackend.model.authentication.User;
import sit.kmutt.kanbanspringbackend.repository.UserRepository;

@Configuration
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    @Bean
    @Transactional
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
            createUser(userRepository, argon2, "alice@example.com", "alice123", "Alice");
            createUser(userRepository, argon2, "bob@example.com", "bob123", "Bob");
            createUser(userRepository, argon2, "charlie@example.com", "charlie123", "Charlie");
        };
    }

    private void createUser(UserRepository userRepository, Argon2 argon2,
                            String email, String rawPassword, String username) {
        String hashedPassword = argon2.hash(2, 65536, 1, rawPassword.toCharArray());
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setUsername(username);
        userRepository.save(user);
        log.info("Preloading " + userRepository.save(user));
    }
}
