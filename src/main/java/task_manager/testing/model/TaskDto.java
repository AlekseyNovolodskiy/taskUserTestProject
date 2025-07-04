package task_manager.testing.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private String taskName;
    private String taskDescription;
    private LocalDateTime expiredAT;
    private TaskStatus status;
    private TaskPriority priority;
}
