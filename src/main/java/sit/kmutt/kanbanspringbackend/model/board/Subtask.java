package sit.kmutt.kanbanspringbackend.model.board;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
