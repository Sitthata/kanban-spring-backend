package sit.kmutt.kanbanspringbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.kmutt.kanbanspringbackend.model.authentication.User;
import sit.kmutt.kanbanspringbackend.model.board.Board;
import sit.kmutt.kanbanspringbackend.model.board.Column;
import sit.kmutt.kanbanspringbackend.model.dtos.BoardDTO;
import sit.kmutt.kanbanspringbackend.model.dtos.ColumnDTO;
import sit.kmutt.kanbanspringbackend.repository.BoardRepository;
import sit.kmutt.kanbanspringbackend.repository.UserRepository;
import sit.kmutt.kanbanspringbackend.utils.DTOConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public List<BoardDTO> findAllBoardForCurrentUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Board> boards = boardRepository.findAllByUser(user);
        return boards.stream()
                .map(DTOConverter::convertToBoardDTO)
                .collect(Collectors.toList());
    }
}
