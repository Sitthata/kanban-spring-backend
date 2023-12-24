package sit.kmutt.kanbanspringbackend.model.dtos;

import java.util.List;

public record BoardDTO(String name, List<ColumnDTO> columns) {
}
