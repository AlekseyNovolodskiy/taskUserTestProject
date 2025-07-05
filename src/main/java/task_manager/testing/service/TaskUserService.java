package task_manager.testing.service;

import task_manager.testing.model.CommentsDto;
import task_manager.testing.model.TaskDto;
import task_manager.testing.model.TaskUserDto;

import java.util.List;

public interface TaskUserService {

    void updateTask(TaskUserDto taskDto, String email);

    List<TaskDto> showAllTasks(String email);

    List<CommentsDto> showTasksComments(String taskName, String email);

    void leaveTasksComments(String taskName,String comment, String name);
}
