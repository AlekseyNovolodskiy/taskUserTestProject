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
import static task_manager.testing.service.implementation.TaskUserServiceImpl.*;

@Service
@RequiredArgsConstructor
public class TaskAdminServiceImpl implements TaskAdminService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    @Override
    public TaskDto createTaskForUser(TaskDto taskDto, Integer userId) {

        boolean present = taskRepository.findByTaskName(taskDto.getTaskName()).isPresent();

        UserEntity userEntitiesById = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        TaskEntity taskEntity = new TaskEntity();

        if (!present) {
            taskEntity.setCreationAT(LocalDateTime.now());
            taskEntity.setTaskDescription(taskDto.getTaskDescription());
            taskEntity.setTaskName(taskDto.getTaskName());
            taskEntity.setExpiredAT(taskDto.getExpiredAT());
            taskEntity.setUser(userEntitiesById);
            taskRepository.save(taskEntity);

        }else {
            throw new TaskException(TASK_EXIST);
        }

        return mapper.taskEntityToTaskDto(taskEntity);
    }

    @Override
    public String deleteUserTask(TaskDto taskDto, Integer userId) {

        TaskEntity taskEntitiesWithOutOptional = taskRepository.findTaskEntitiesWithOutOptional(taskDto.getTaskName());

        if (!isNull(taskEntitiesWithOutOptional)&& userRepository.existsById(Long.valueOf(userId))) {
            taskRepository.delete(taskEntitiesWithOutOptional);
        }
        else {
            throw new TaskException(TASK_NO_EXIST);
        }
        return "Задача удалена";
    }

    @Override
    @Transactional
    public TaskDto updateUserTask(TaskDto taskDto, Integer userId) {

        TaskEntity taskEntitiesWithOutOptional = taskRepository.findTaskEntitiesWithOutOptional(taskDto.getTaskName());

        if (!isNull(taskEntitiesWithOutOptional) && userRepository.existsById(Long.valueOf(userId))) {
            taskEntitiesWithOutOptional.setTaskDescription(taskDto.getTaskDescription());
            taskEntitiesWithOutOptional.setTaskName(taskDto.getTaskName());
            taskEntitiesWithOutOptional.setExpiredAT(taskDto.getExpiredAT());
        }
        else {
            throw new TaskException(TASK_NO_EXIST);
        }

        return mapper.taskEntityToTaskDto(taskEntitiesWithOutOptional);
    }

    @Override
    public List<TaskDto> showAllTask() {

        List<TaskEntity> allTaskList = taskRepository.findAll();

        return mapper.taskEntityToTaskDto(allTaskList);
    }
}
