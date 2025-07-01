package task_manager.testing.service;

import task_manager.testing.model.TaskDto;

import java.util.List;

public interface TaskUserService {
    void createTask(TaskDto taskDto,String email);

    void updateTask(TaskDto taskDto, String email);

    void deleteTask(TaskDto taskDto, String email);

    List<TaskDto> showAllTasks(String email);

}
