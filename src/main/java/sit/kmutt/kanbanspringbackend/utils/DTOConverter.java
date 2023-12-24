package sit.kmutt.kanbanspringbackend.utils;

import sit.kmutt.kanbanspringbackend.model.board.Board;
import sit.kmutt.kanbanspringbackend.model.board.Column;
import sit.kmutt.kanbanspringbackend.model.board.Subtask;
import sit.kmutt.kanbanspringbackend.model.board.Task;
import sit.kmutt.kanbanspringbackend.model.dtos.BoardDTO;
import sit.kmutt.kanbanspringbackend.model.dtos.ColumnDTO;
import sit.kmutt.kanbanspringbackend.model.dtos.SubtaskDTO;
import sit.kmutt.kanbanspringbackend.model.dtos.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
    public static BoardDTO convertToBoardDTO(Board board) {
        List<ColumnDTO> columnDTOs = board.getColumns().stream()
                .map(DTOConverter::convertToColumnDTO)
                .collect(Collectors.toList());
        return new BoardDTO(board.getName(), columnDTOs);
    }

    public static ColumnDTO convertToColumnDTO(Column column) {
        List<TaskDTO> taskDTOs = column.getTasks().stream()
                .map(DTOConverter::convertToTaskDTO)
                .collect(Collectors.toList());
        return new ColumnDTO(column.getName(), taskDTOs);
    }

    public static TaskDTO convertToTaskDTO(Task task) {
        List<SubtaskDTO> subtaskDTOs = task.getSubtask().stream()
                .map(DTOConverter::convertToSubtaskDTO)
                .collect(Collectors.toList());
        return new TaskDTO(task.getTitle(), task.getDescription(), task.getStatus(), subtaskDTOs);
    }

    public static SubtaskDTO convertToSubtaskDTO(Subtask subtask) {
        return new SubtaskDTO(subtask.getTitle(), subtask.isCompleted());
    }
}
