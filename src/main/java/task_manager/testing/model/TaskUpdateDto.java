package task_manager.testing.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdateDto {
    private String taskDescription;
    private LocalDateTime expiredAT;
}
