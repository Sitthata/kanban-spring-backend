package sit.kmutt.kanbanspringbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.kmutt.kanbanspringbackend.model.authentication.User;
import sit.kmutt.kanbanspringbackend.model.dtos.BoardDTO;
import sit.kmutt.kanbanspringbackend.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public ResponseEntity<List<BoardDTO>> getBoardsForCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        List<BoardDTO> boards = boardService.findAllBoardForCurrentUser(currentUser.getId());
        return ResponseEntity.ok(boards);
    }
}
