package sit.kmutt.kanbanspringbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.kmutt.kanbanspringbackend.model.authentication.User;
import sit.kmutt.kanbanspringbackend.model.board.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUser(User user);
}
