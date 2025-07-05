package task_manager.testing.service;

import task_manager.testing.model.TaskDto;

import java.util.List;

public interface TaskAdminService{

    TaskDto createTaskForUser (TaskDto taskDto);
    String deleteUserTask(TaskDto taskDto);
    TaskDto updateUserTask(TaskDto taskDto);
    List<TaskDto> showAllTask();

    void leaveAdminTasksComments(String taskName, String comment, String name);
}
