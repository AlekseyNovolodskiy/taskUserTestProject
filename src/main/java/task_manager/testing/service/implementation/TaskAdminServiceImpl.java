package task_manager.testing.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task_manager.testing.entity.CommentsEntity;
import task_manager.testing.entity.TaskEntity;
import task_manager.testing.entity.UserEntity;
import task_manager.testing.exeption.TaskException;
import task_manager.testing.exeption.UserException;
import task_manager.testing.mapper.TaskMapper;
import task_manager.testing.model.TaskDto;
import task_manager.testing.repositories.CommentsRepository;
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
    private final CommentsRepository commentsRepository;
    private final TaskMapper mapper;

    @Override
    public TaskDto createTaskForUser(TaskDto taskDto, String email) {


        boolean present = taskRepository.findByTaskName(taskDto.getTaskName()).isPresent();

        UserEntity byUserName = userRepository.findByUserName(taskDto.getExecutor())
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        UserEntity userEntitiesByAuthor = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        TaskEntity taskEntity = new TaskEntity();

        if (!present) {
            taskEntity.setCreationAT(LocalDateTime.now());
            taskEntity.setTaskDescription(taskDto.getTaskDescription());
            taskEntity.setTaskName(taskDto.getTaskName());
            taskEntity.setExpiredAT(taskDto.getExpiredAT());
            taskEntity.setUser(byUserName);
            taskEntity.setAuthor(userEntitiesByAuthor.getNameOfUser());
            taskEntity.setPriority(taskDto.getPriority());
            taskEntity.setStatus(taskDto.getStatus());
            taskRepository.save(taskEntity);

        }else {
            throw new TaskException(TASK_EXIST);
        }

        return mapper.taskEntityToTaskDto(taskEntity);
    }

    @Override
    public String deleteUserTask(TaskDto taskDto, String email) {

        TaskEntity taskEntitiesWithOutOptional = taskRepository.findTaskEntitiesWithOutOptional(taskDto.getTaskName());

        UserEntity userEntitiesByAuthor = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        if (!isNull(taskEntitiesWithOutOptional)&& userRepository.existsById(userEntitiesByAuthor.getId())) {
            taskRepository.delete(taskEntitiesWithOutOptional);
        }
        else {
            throw new TaskException(TASK_NO_EXIST);
        }
        return "Задача удалена";
    }

    @Override
    @Transactional
    public TaskDto updateUserTask(TaskDto taskDto, String email) {

        UserEntity byUserName = userRepository.findByUserName(taskDto.getExecutor())
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        TaskEntity taskEntitiesWithOutOptional = taskRepository.findTaskEntitiesWithOutOptional(taskDto.getTaskName());

        UserEntity userEntitiesByAuthor = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        if (!isNull(taskEntitiesWithOutOptional) && userRepository.existsById(userEntitiesByAuthor.getId())) {
            taskEntitiesWithOutOptional.setUser(byUserName);
            taskEntitiesWithOutOptional.setTaskDescription(taskDto.getTaskDescription());
            taskEntitiesWithOutOptional.setTaskName(taskDto.getTaskName());
            taskEntitiesWithOutOptional.setExpiredAT(taskDto.getExpiredAT());
            taskEntitiesWithOutOptional.setAuthor(userEntitiesByAuthor.getNameOfUser());
            taskEntitiesWithOutOptional.setStatus(taskDto.getStatus());
            taskEntitiesWithOutOptional.setPriority(taskDto.getPriority());
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

    @Override
    public void leaveAdminTasksComments(String taskName, String comment, String name) {

        UserEntity userEntity = userRepository.findUserEntitiesByEmail(name).orElseThrow(() -> new UserException(USER_NO_EXIST));

        TaskEntity taskEntity = taskRepository.findByTaskName(taskName)
                .orElseThrow(() -> new TaskException(TASK_NO_EXIST));

        CommentsEntity commentsEntity = new CommentsEntity();

        String actualComment = userEntity.getRole()+" "+userEntity.getNameOfUser()+" leave comment: " +comment;
        commentsEntity.setComment(actualComment);
        commentsEntity.setTask(taskEntity);
        commentsEntity.setUser(userEntity);
        commentsRepository.save(commentsEntity);
    }
}
