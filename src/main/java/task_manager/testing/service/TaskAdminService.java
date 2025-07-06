package task_manager.testing.service;

import task_manager.testing.model.TaskDto;

import java.util.List;

public interface TaskAdminService{

    TaskDto createTaskForUser (TaskDto taskDto, String email);
    String deleteUserTask(TaskDto taskDto, String email);
    TaskDto updateUserTask(TaskDto taskDto, String email);
    List<TaskDto> showAllTask();

    void leaveAdminTasksComments(String taskName, String comment, String name);
}
