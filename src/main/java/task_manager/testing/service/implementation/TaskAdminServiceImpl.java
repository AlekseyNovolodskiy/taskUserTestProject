package task_manager.testing.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task_manager.testing.entity.TaskEntity;
import task_manager.testing.entity.UserEntity;
import task_manager.testing.exeption.TaskException;
import task_manager.testing.exeption.UserException;
import task_manager.testing.mapper.TaskMapper;
import task_manager.testing.model.TaskDto;
import task_manager.testing.repositories.TaskRepository;
import task_manager.testing.repositories.UserRepository;
import task_manager.testing.service.TaskAdminService;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static task_manager.testing.service.implementation.TaskUserServiceImpl.TASK_EXIST;
import static task_manager.testing.service.implementation.TaskUserServiceImpl.USER_NO_EXIST;

@Service
@RequiredArgsConstructor
public class TaskAdminServiceImpl implements TaskAdminService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    @Override
    public TaskDto createTaskForUser(TaskDto taskDto, Integer userId) {

        TaskEntity byTaskName = taskRepository.findByTaskName(taskDto.getTaskName());

        UserEntity userEntitiesById = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UserException(USER_NO_EXIST));

        if (isNull(byTaskName)) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setCreationAT(LocalDateTime.now());
            taskEntity.setTaskDescription(taskDto.getTaskDescription());
            taskEntity.setTaskName(taskDto.getTaskName());
            taskEntity.setExpiredAT(taskDto.getExpiredAT());
            taskEntity.setUser(userEntitiesById);
            taskRepository.save(taskEntity);

            return mapper.taskEntityToTaskDto(taskEntity);
        } else {
            throw new TaskException(TASK_EXIST);
        }

    }

    @Override
    public String deleteUserTask(TaskDto taskDto, Integer userId) {

        TaskEntity byTaskName = taskRepository.findByTaskName(taskDto.getTaskName());

        if (!isNull(byTaskName) && userRepository.existsById(Long.valueOf(userId))) {
            taskRepository.delete(byTaskName);
        }
        return "Задача удалена";
    }

    @Override
    @Transactional
    public TaskDto updateUserTask(TaskDto taskDto, Integer userId) {
        TaskEntity byTaskName = taskRepository.findByTaskName(taskDto.getTaskName());

        if (!isNull(byTaskName) && userRepository.existsById(Long.valueOf(userId))) {
            byTaskName.setTaskDescription(taskDto.getTaskDescription());
            byTaskName.setTaskName(taskDto.getTaskName());
            byTaskName.setExpiredAT(taskDto.getExpiredAT());
        }
        return mapper.taskEntityToTaskDto(byTaskName);
    }

    @Override
    public List<TaskDto> showAllTask() {

        List<TaskEntity> allTaskList = taskRepository.findAll();

        return mapper.taskEntityToTaskDto(allTaskList);
    }
}
