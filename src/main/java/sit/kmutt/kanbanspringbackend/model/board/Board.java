package sit.kmutt.kanbanspringbackend.model.board;

import jakarta.persistence.*;
import lombok.Data;
import sit.kmutt.kanbanspringbackend.model.authentication.User;

import java.util.List;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "board_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Column> columns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
