package task_manager.testing.service.implementation.util;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import task_manager.testing.entity.TaskEntity;
import task_manager.testing.entity.UserEntity;
import task_manager.testing.model.TaskPriority;
import task_manager.testing.model.TaskStatus;
import task_manager.testing.model.TaskUserDto;
import task_manager.testing.model.UserRole;

import java.time.LocalDateTime;

public class TaskUserUtilTest {
    public static TaskUserDto getTaskUserDto() {
        TaskUserDto taskUserDto = new TaskUserDto();
        taskUserDto.setTaskName("newTask");
        taskUserDto.setStatus(TaskStatus.INPROGRESS);

        return taskUserDto;
    }

    public static TaskEntity getTaskEntity(UserEntity user) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName("newTask");
        taskEntity.setTaskDescription("newDescription");
        taskEntity.setExpiredAT(LocalDateTime.parse("2028-05-11T15:30:45"));
        taskEntity.setCreationAT(LocalDateTime.parse("2028-05-11T15:30:45"));
        taskEntity.setPriority(TaskPriority.LOW);
        taskEntity.setStatus(TaskStatus.ATSTART);
        taskEntity.setAuthor("useradmin");
        taskEntity.setUser(user);
        return taskEntity;
    }

    public static UserEntity getUserEntity() {
        UserEntity userExecutor = new UserEntity();
        userExecutor.setId(1L);
        userExecutor.setNameOfUser("userExecutor");
        userExecutor.setEmail("userExecutorEmail");
        userExecutor.setRole(UserRole.USER);
        userExecutor.setPassword("$2a$10$1fnktPcyCD8aqmWaan/qz.VK0eZbZpR5LisdpMnW5PzHWF1agx2wy");
        return userExecutor;
    }
    public static UserEntity getWrongUserEntity() {
        UserEntity wrongExecutor = new UserEntity();
        wrongExecutor.setId(2L);
        wrongExecutor.setNameOfUser("userExecutorWrong");
        wrongExecutor.setEmail("userExecutorEmailWrong");
        wrongExecutor.setRole(UserRole.USER);
        wrongExecutor.setPassword("$2a$10$1fnktPcyCD8aqmWaan/qz.VK0eZbZpR5LisdpMnW5PzHWF1agx2wy");
        return wrongExecutor;
    }

}
