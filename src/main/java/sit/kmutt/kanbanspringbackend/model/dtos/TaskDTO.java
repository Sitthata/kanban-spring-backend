package sit.kmutt.kanbanspringbackend.model.dtos;

import java.util.List;

public record TaskDTO(String title, String description, String status, List<SubtaskDTO> subtasks) {
}
