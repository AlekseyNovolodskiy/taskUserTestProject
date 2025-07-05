package task_manager.testing.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskUserDto {

    @NotBlank
    private String taskName;

    @NotNull
    private TaskStatus status;
}
