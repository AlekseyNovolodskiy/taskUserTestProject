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
import task_manager.testing.service.TaskUserService;

import java.time.LocalDateTime;
import java.util.List;


import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TaskUserServiceImpl implements TaskUserService {

    public static final String USER_NO_EXIST = "Пользователь не найден";
    public static final String TASK_EXIST = "Задача с таким именем существует";
    public static final String TASK_NO_EXIST = "Задача с таким именем не существует";

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    @Override
    public void createTask(TaskDto taskDto, String email) {

        boolean byTaskName = taskRepository.findByTaskName(taskDto.getTaskName()).isPresent();
        UserEntity userEntitiesByEmail = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        if (!byTaskName) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setCreationAT(LocalDateTime.now());
            taskEntity.setTaskDescription(taskDto.getTaskDescription());
            taskEntity.setTaskName(taskDto.getTaskName());
            taskEntity.setExpiredAT(taskDto.getExpiredAT());
            taskEntity.setUser(userEntitiesByEmail);
            taskRepository.save(taskEntity);
        } else {
            throw new TaskException(TASK_EXIST);
        }
    }

    @Transactional
    @Override
    public void updateTask(TaskDto taskDto, String email) {

        TaskEntity byTaskName = taskRepository.findByTaskName(taskDto.getTaskName())
                .orElseThrow(() -> new TaskException(TASK_NO_EXIST));

        if (!isNull(byTaskName) && userRepository.existsByEmail(email)) {
            byTaskName.setTaskDescription(taskDto.getTaskDescription());
            byTaskName.setTaskName(taskDto.getTaskName());
            byTaskName.setExpiredAT(taskDto.getExpiredAT());
        }
    }

    @Override
    public void deleteTask(TaskDto taskDto, String email) {

        TaskEntity byTaskName = taskRepository.findByTaskName(taskDto.getTaskName())
                .orElseThrow(()->new TaskException(TASK_NO_EXIST));

        if (!isNull(byTaskName) && userRepository.existsByEmail(email)) {
            taskRepository.delete(byTaskName);
        }
    }

    @Override
    public List<TaskDto> showAllTasks(String email) {

        UserEntity userEntity = userRepository.findUserEntitiesByEmail(email).orElseThrow(() -> new UserException(USER_NO_EXIST));
        List<TaskEntity> taskListByUserId = taskRepository.findTaskListByUserId(userEntity.getId());

        return mapper.taskEntityToTaskDto(taskListByUserId);
    }
}
