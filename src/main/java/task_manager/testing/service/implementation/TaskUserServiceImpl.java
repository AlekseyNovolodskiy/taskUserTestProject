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
import task_manager.testing.model.CommentsDto;
import task_manager.testing.model.TaskDto;
import task_manager.testing.model.TaskUserDto;
import task_manager.testing.repositories.CommentsRepository;
import task_manager.testing.repositories.TaskRepository;
import task_manager.testing.repositories.UserRepository;
import task_manager.testing.service.TaskUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TaskUserServiceImpl implements TaskUserService {

    public static final String USER_NO_EXIST = "Пользователь не найден";
    public static final String TASK_EXIST = "Задача с таким именем существует";
    public static final String TASK_NO_EXIST = "Задача с таким именем не существует";
    public static final String NOT_ACCESS = "Вы не имеете доступа к данной задаче";

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final CommentsRepository commentsRepository;


    @Transactional
    @Override
    public void updateTask(TaskUserDto taskDto, String email) {

        TaskEntity byTaskName = taskRepository.findByTaskName(taskDto.getTaskName())
                .orElseThrow(() -> new TaskException(TASK_NO_EXIST));
       UserEntity userEntitiesByEmail = userRepository.findUserEntitiesByEmail(email)
               .orElseThrow(() -> new UserException(USER_NO_EXIST));

        if(!byTaskName.getUser().getId().equals(userEntitiesByEmail.getId())){
            throw new UserException(NOT_ACCESS);
        }

            byTaskName.setStatus(taskDto.getStatus());

    }


    @Override
    public List<TaskDto> showAllTasks(String email) {

        UserEntity userEntity = userRepository.findUserEntitiesByEmail(email).orElseThrow(() -> new UserException(USER_NO_EXIST));
        List<TaskEntity> taskListByUserId = taskRepository.findTaskListByUserId(userEntity.getId());

        return mapper.taskEntityToTaskDto(taskListByUserId);
    }

    @Override
    public List<CommentsDto> showTasksComments(String taskName,String email) {

        TaskEntity taskEntity = taskRepository.findByTaskName(taskName)
                .orElseThrow(() -> new TaskException(TASK_NO_EXIST));
        UserEntity userEntitiesByEmail = userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new UserException(USER_NO_EXIST));

        if(!taskEntity.getUser().getId().equals(userEntitiesByEmail.getId())){
            throw new UserException(NOT_ACCESS);
        }

        return mapper.commentsEntityToCommentsDto(commentsRepository.findByTask(taskEntity));
    }

    @Override
    public void leaveTasksComments(String taskName,String comment, String name) {
        UserEntity userEntity = userRepository.findUserEntitiesByEmail(name).orElseThrow(() -> new UserException(USER_NO_EXIST));
        TaskEntity taskEntity = taskRepository.findByTaskName(taskName)
                .orElseThrow(() -> new TaskException(TASK_NO_EXIST));

        if(!taskEntity.getUser().getId().equals(userEntity.getId())){
            throw new UserException(NOT_ACCESS);
        }
        CommentsEntity commentsEntity = new CommentsEntity();

        String actualComment = userEntity.getRole()+" "+userEntity.getNameOfUser()+" leave comment: " +comment;
        commentsEntity.setComment(actualComment);
        commentsEntity.setTask(taskEntity);
        commentsEntity.setUser(userEntity);
        commentsRepository.save(commentsEntity);
    }

}
