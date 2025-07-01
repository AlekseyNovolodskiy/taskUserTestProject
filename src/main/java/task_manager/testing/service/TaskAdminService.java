package task_manager.testing.service;

import task_manager.testing.model.TaskDto;

import java.util.List;

public interface TaskAdminService{

    TaskDto createTaskForUser (TaskDto taskDto, Long UserId);
    String deleteUserTask(TaskDto taskDto, Long UserId);
    TaskDto updateUserTask(TaskDto taskDto, Long UserId);
    List<TaskDto> showAllTask();

}
