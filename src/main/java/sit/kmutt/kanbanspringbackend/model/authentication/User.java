package sit.kmutt.kanbanspringbackend.model.authentication;

import jakarta.persistence.*;
import lombok.*;
import sit.kmutt.kanbanspringbackend.model.board.Board;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards;
}
