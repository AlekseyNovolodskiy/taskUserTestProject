package task_manager.testing.service;

import task_manager.testing.model.TaskDto;

import java.util.List;

public interface TaskAdminService{

    TaskDto createTaskForUser (TaskDto taskDto, Integer UserId);
    String deleteUserTask(TaskDto taskDto, Integer UserId);
    TaskDto updateUserTask(TaskDto taskDto, Integer UserId);
    List<TaskDto> showAllTask();

    void leaveAdminTasksComments(String taskName, String comment, String name);
}
