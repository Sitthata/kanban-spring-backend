package sit.kmutt.kanbanspringbackend.model.dtos;

import java.util.List;

public record ColumnDTO(String name, List<TaskDTO> tasks) {
}
