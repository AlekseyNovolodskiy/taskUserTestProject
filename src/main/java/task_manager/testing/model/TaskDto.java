package task_manager.testing.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    @NotBlank
    private String taskName;
    @NotBlank
    private String taskDescription;
    @NotNull
    private LocalDateTime expiredAT;
    @NotNull
    private TaskStatus status;
    @NotNull
    private TaskPriority priority;
}
