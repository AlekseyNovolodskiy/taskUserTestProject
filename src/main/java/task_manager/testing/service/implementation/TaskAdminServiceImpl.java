package task_manager.testing.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task_manager.testing.model.TaskDto;
import task_manager.testing.service.TaskAdminService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAdminServiceImpl implements TaskAdminService {
    @Override
    public TaskDto createTaskForUser(TaskDto taskDto, Long UserId) {
        return null;
    }

    @Override
    public String deleteUserTask(TaskDto taskDto, Long UserId) {
        return "";
    }

    @Override
    public TaskDto updateUserTask(TaskDto taskDto, Long UserId) {
        return null;
    }

    @Override
    public List<TaskDto> showAllTask() {
        return List.of();
    }
}
